# JUC 
java.util.concurrent:包含并发编程常用工具类

volatile 关键字
================

当多个线程进行操作共享数据时,可以保证内存中的数据是可见的;相较于 synchronized 是一种较为轻量级的同步策略

volatile 不具备"互斥性"，不能保证变量的"原子性"


内存可见性：多个线程操作共享数据时，彼此不可见


i++ 的原子性问题
================

i++的操作实际上分为三个步骤: "读-改-写";
原子性: 就是"i++"的"读-改-写"是不可分割的三个步骤;
原子变量: JDK1.5 以后, java.util.concurrent.atomic包下,提供了常用的原子变量;
原子变量中的值,使用 volatile 修饰,保证了内存可见性;
CAS(Compare-And-Swap) 算法保证数据的原子性;

CAS 算法
================

CAS(Compare-And-Swap) 算法是硬件对于并发的支持,针对多处理器操作而设计的处理器中的一种特殊指令,用于管理对共享数据的并发访问;

CAS 是一种无锁的非阻塞算法的实现;

CAS 包含了三个操作数:

需要读写的内存值: V
进行比较的预估值: A
拟写入的更新值: B

当且仅当 V == A 时, V = B, 否则,将不做任何操作;


并发容器类
================

ConcurrentHashMap
---------------------

ConcurrentHashMap 同步容器类是 Java5 增加的一个线程安全的哈希表;介于 HashMap 与 Hashtable 之间;

内部采用"锁分段"机制替代Hashtable的独占锁,进而提高性能;

此包还提供了设计用于多线程上下文中的Collection实现: ConcurrentHashMap,ConcurrentSkipListMap，ConcurrentSkipListSet, CopyOnWriteArrayList 和 CopyOnWriteArraySet

当期望许多线程访问一个给定collection时,ConcurrentHashMap通常优于同步的HashMap;ConcurrentSkipListMap通常优于同步的TreeMap;

当期望的读数和遍历远远大于列表的更新数时, CopyOnWriteArrayList优于同步的ArrayList;

CountDownLatch(闭锁)
---------------------

CountDownLatch是一个同步辅助类,在完成一组正在其他线程中执行的操作之前,它允许一个或多个线程一直等待;


创建执行线程的方式
---------------------

相较于实现 Runnable 接口的方式,实现 Callable 接口类中的方法可以有返回值,并且可以抛出异常;


同步锁(Lock)
---------------------


ReadWriteLock(读写锁)
---------------------


线程池
---------------------

线程池提供了一个线程队列,队列中保存着所有等待状态的线程;

避免了创建与销毁线程的额外开销,提高了响应速度;

线程池的体系结构
java.util.concurrent.Executor: 负责线程的使用和调度的根接口;
ExecutorService: 子接口,线程池的主要接口;
ThreadPoolExecutor: 线程池的实现类;
ScheduledExecutorService: 子接口,负责线程的调度;
ScheduledThreadPoolExecutor: 继承了线程池的实现类,实现了负责线程调度的子接口;

工具类: Executors
ExecutorService newFixedThreadPool(): 创建固定大小的线程池;
ExecutorService newCachedThreadPool(): 缓存线程池,线程池中线程的数量不固定,可以根据需求自动更改数量;
ExecutorService newSingleThreadExecutor(): 创建单个线程池, 线程池中只有一个线程;
ScheduledExecutorService newScheduledThreadPool(): 创建固定大小的线程,可以延时或定时的执行任务;


Fork/Join 框架
---------------------




参考：[Java 之 JUC](https://www.cnblogs.com/linkworld/p/7819270.html)
