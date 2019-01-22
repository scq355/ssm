package com.sangame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by admin on 2017/5/11.
 */
public class JedisUtil {
    protected static final Logger log = LoggerFactory.getLogger(JedisUtil.class);

    /**
     * 私有构造器
     */
    private JedisUtil() {}

    private static Map<String, JedisPool> maps = new HashMap<String, JedisPool>();

    private static Properties properties = new Properties();


    /**
     * 获取连接池
     * @return  连接池实例
     */
    public static JedisPool getPool() {
        try {
            FileInputStream fis = new FileInputStream("G:\\svn\\datafilter-console\\src\\main\\resources\\config.properties");
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage(), e);
        }
        String host = properties.getProperty("redis.host");
        log.info("redis host --------> " + host);
        String port = properties.getProperty("redis.port");
        String password = properties.getProperty("redis.passwd");
        String key = host + ":" + port;
        String maxWaitMills = properties.getProperty("redis.max.wait");
        String maxTotal = properties.getProperty("redis.max.total");
        String maxIdle = properties.getProperty("redis.max.idle");
        String timeout = properties.getProperty("redis.timeout");
        log.info("connect to redis :" + host + "|" + port);
        JedisPool pool = null;
        if (!maps.containsKey(key)) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.parseInt(maxTotal));
            config.setMaxIdle(Integer.parseInt(maxIdle));
            config.setMaxWaitMillis(Long.parseLong(maxWaitMills));
            config.setTestOnBorrow(false);
            config.setTestOnReturn(true);
            config.setMinEvictableIdleTimeMillis(1800000);
            config.setTestWhileIdle(false);
            try {
                /**
                 * 如果你遇到 java.net.SocketTimeoutException: Read timed out
                 * exception的异常信息 请尝试在构造JedisPool的时候设置自己的超时值.
                 * JedisPool默认的超时时间是2秒(单位毫秒)
                 */
                pool = new JedisPool(config, host, Integer.parseInt(port), Integer.parseInt(timeout), password);
                log.info("Exsits,ActiveNumbers::" + pool.getNumActive());
                maps.put(key, pool);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage(), e);
            }
        } else {
            pool = maps.get(key);
            log.info("Exsits,ActiveNumbers::" + pool.getNumActive());
            if (pool.isClosed()) {
                maps.remove(key);
                getPool();
            }
        }
        return pool;
    }

    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
     */
    public static class RedisUtilHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static JedisUtil instance = new JedisUtil();
    }

    /**
     * 当getInstance方法第一次被调用的时候，它第一次读取
     * RedisUtilHolder.instance，导致RedisUtilHolder类得到初始化；而这个类在装载并被初始化的时候，会初始化它的静
     * 态域，从而创建RedisUtil的实例，由于是静态的域，因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来保证它的线程安全性。
     * 这个模式的优势在于，getInstance方法并没有被同步，并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本。
     * @return
     */
    public static JedisUtil getInstance() {
        return RedisUtilHolder.instance;
    }

    /**
     * 获取Redis实例.
     * @return Redis工具类实例
     */
    public Jedis getJedis() {
        Jedis jedis = null;
        int count = 0;
        do {
            try {
                jedis = getPool().getResource();
            } catch (Exception e) {
                log.error("get redis master1 failed!", e);
                getPool().close();
            }
            count++;
        } while (jedis == null && count < 3);
        return jedis;
    }

    /**
     * 释放redis实例到连接池
     * @param jedis redis实例
     * @param ip
     * @param port
     */
    public void closeJedis(Jedis jedis, String ip, int port) {
        if (jedis != null) {
            getPool().close();
        }
    }
}
