final、finally、finalize

final:修饰符，关键字，可以修饰类，方法，属性（变量）。修饰类，该类不能派生子类，不能被继承，不能被abstract修饰
    修饰方法：方法在使用过程中不可修改，不可被重载
    修饰变量：变量声明时给出初始值，之后只能读，不能改
    
finally：配合异常处理try...catch来执行一些资源关闭，清理的功能代码，try里包含continue，break，return，try块结束后，finally块也会执行

finalize：方法名，在Object类中，在垃圾收集器回收没有被引用的对象之前做一些必要的清理工作


Exception、error、运行时异常和一般异常有何异同

所有异常对象继承Throwable，下分Error跟Exception

Error:代码运行时JVM出现错误，不可捕捉

Exception:分为RuntimeException和IOException

unchecked exception（非检查异常）：包括运行时异常（RuntimeException）和派生于Error类的异常

checked exception（检查异常，编译异常，必须要处理的异常）:IOException,SQLException，编译器强制程序员必须进行捕获处理


int 和 Integer 有什么区别，Integer的值缓存范围

Integer2intTest

JVM会自动维护八种基本类型的常量池，int常量池中初始化-128~127的范围，所以当为Integer i=127时，在自动装箱过程中是取自常量池中的数值，而当Integer i=128时，128不在常量池范围内，所以在自动装箱过程中需new 128，所以地址不一样。

Integer对象的hash值为数值本身


重载和重写的区别

重写：Override，子类方法覆盖父类方法，返回值，方法名，参数都相同，子类方法异常小于父类方法异常，子类访问范围大于等于父类方法访问范围

重载：同一个类中，俩个以上的同名方法，参数不同，以统一的方式处理不同类型数据的一种手段


hashCode和equals方法的区别与联系

equals:用于比较对象的内容是否相等（覆盖以后），当覆盖了equals方法时，比较对象是否相等将通过覆盖后的equals方法进行比较（判断对象的内容是否相等）

hashCode：主要是用于查找的快捷性，如Hashtable，HashMap等，hashCode是用来在散列存储结构中确定对象的存储地址的

将对象放入到集合中时，首先判断要放入对象的hashCode值与集合中的任意一个元素的hashCode值是否相等，如果不相等直接将该对象放入集合中。如果hashCode值相等，然后再通过equals方法判断要放入对象与集合中的任意一个对象是否相等，如果equals判断不相等，直接将该元素放入到集合中，否则不放入


什么是Java序列化和反序列化，如何实现Java序列化？或者请解释Serializable 接口的作用

序列化：把对象转换为字节序列的过程称为对象的序列化

反序列化：把字节序列恢复为对象的过程称为对象的反序列化

SerialVersionUID属性：创建A类的对象a并进行序列化传输时，如果此时我们修改了A类，增加了某些新的属性，这时候如果不对其进行判断而进行反序列化的话，将会导致运行时异常，两者类型不匹配。因此，这里使用SerialVersionUTD属性，该属性用来唯一标识一个类的版本


List、Set、Map的区别

List:有序线性容器，允许重复对象，可插入多个Null元素，频繁访问ArrayList，频繁插入：LinkedList

Set:无序非线性集合，不允许重复对象，重复元素会被覆盖，元素位置由元素的hashCode决定（位置其实是固定的），只允许一个Null元素

List,Set是Collection接口的子接口

Map:元素基于K-V键值对的集合，不是Collection的子接口


Vector & ArrayList 

序列化：ArrayList比Vector安全，ArrayList提供的writeObject和readObject方法来实现定制序列化，而Vector只是提供了writeObject方法，并没有完全实现定制序列化

线程安全：同点在于Vector是线性安全的，ArrayList是非线性安全的，synchronized

扩容：ArrayList容量不足增加0.5倍，Vector当capacityIncrement实例变量大于0时，扩充为原有容量加上capacityIncrement的容量值。否则增加0.5倍


LinkedList & ArrayList

数据结构：ArrayList：List接口的可变长的数组实现，LinkedList：List接口的双向链表实现


HashMap & Hashtable

HashMap:HashMapTest


ConcurrentHashMap

1.7 Segment + HashEntry

HashEntry对象几乎是不可变的(只能改变Value的值)，key、hash和next指针都是final的，链表头部操作:降低操作复杂度，Volatile：保证内存可见性

不允许用null作为键和值，所以当读线程读到某个HashEntry的value为null时，便知道产生了冲突 —— 发生了重排序现象，则加锁重读

1.8 Node + CAS + Synchronized


BIO、NIO、AIO


内存可见性：当程序运行的时，JVM会为每一个线程（或者每一个执行任务的线程）都会分配一个独立的缓存，用于提高效率

多个线程同时操作共享数据：当某个线程需要改变共享数据时，会把共享数据读到该线程的缓存中，然后做修改操作，最后将修改之后的值写到主存中，在第一个线程操作共享数据过程中，其他线程从主存中读取共享数据，，此时共享数据正在被第一个线程修改

原因：多个线程在操作共享数据的时候，对共享数据的操作彼此不可见

解决方式：

同步锁：每次操作都会刷新缓存

Volatile:线程直接操作主存数据


锁：乐观锁，悲观锁

悲观锁：假设最坏情况，每次操作数据时都会认为其他操作会修改，每次操作数据时都会上锁。其他对数据发起的操作都会进入阻塞状态，直到该操作拿到锁。

应用场景：行锁，表锁，读写锁等，synchronized关键字的实现

特点：独占性：多线程竞争下，加锁，释放锁会导致比较多的上下文切换和调度延时，引起性能问题

乐观锁：对业务场景做乐观假设，每次读取数据时候都认为不会被修改，不会上锁，在更新之前会判断数据是否被修改。（冲突检测，数据更新）

应用场景：多读应用类型

CAS属于乐观锁：当多个线程使用CAS同时更新一个变量时，只有一个线程可以更新成功其他都失败，失败不会被挂起，而是告知本次竞争失败，可以再次尝试







