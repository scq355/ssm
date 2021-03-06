redis分布式锁实现：Redisson（Redlock算法）
以有效方式使用分布式锁所需的最低保证：

互斥性：任何给定时刻，只有一个客户端可以持有锁

无死锁：使锁定资源的客户端崩溃或被分区，也始终可以获取锁定

容错：只要大多数Redis节点启动，客户端就能够获取和释放锁

基于故障转移的实现还不够的原因：

大多数基于Redis的分布式锁库的当前状态：

使用Redis锁定资源的最简单方法是在实例中创建key。key通常使用Redis expires参数指定key的有效时间，key最终会被删除释放。

上述方式存在单点故障，可以添加一个从节点解决单点问题。但是样是不可行的。因为无法实现互斥的安全属性，因为Redis复制是异步的。

这个模型有明显的竞争条件：

客户端A获取主节点中的锁，在写入key之前，主节点挂掉并发送到从节点，从节点被提升为主节点，客户端B获取对已经拥有锁的相同资源的锁，安全违规

SET resource_name my_random_value NX PX 30000

该命令仅在key尚不存在时才设置key（NX选项），过期时间为30000毫秒（PX选项）。key设置为“我的随机值”值。此值必须在所有客户端和所有锁定请求中都是唯一的

基本上使用随机值是为了以安全的方式释放锁，使用一个告诉Redis的脚本：仅当密钥存在且存储在密钥上的值恰好是我期望的值时才删除密钥。这是通过以下Lua脚本完成的

```lua
if redis.call("get",KEYS[1]) == ARGV[1] then
    return redis.call("del",KEYS[1])
else
    return 0
end
```

这样可以避免删除由另一个客户端创建的锁，例如：客户端可能获得锁，在某些操作中被阻止的时间超过锁有效时间（密钥将到期的时间），并且稍后移除已经由某个其他客户端获取的锁

仅使用DEL是不安全的，因为客户端可能会删除另一个客户端的锁，使用上面的脚本而不是每个锁都使用随机字符串“签名”，因此只有在客户端尝试删除锁时，锁才会被删除。
