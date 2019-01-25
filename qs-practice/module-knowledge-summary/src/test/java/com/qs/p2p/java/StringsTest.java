package com.qs.p2p.java;

import org.junit.Test;

/**
 * String、StringBuilder、StringBuffer
 *
 * 运行速度：StringBuilder > StringBuffer > String
 *
 * 线程安全：StringBuilder线程不安全，StringBuffer线程安全（synchronize）
 *
 * String：适用于少量的字符串操作的情况
 * StringBuilder：适用于单线程下在字符缓冲区进行大量操作的情况
 * StringBuffer：适用多线程下在字符缓冲区进行大量操作的情况
 */
public class StringsTest {

    /**
     * String类是不可变的，即字符串常量，所以每次对 String 类型进行改变的时候其实都等同于生成了一个新的 String 对象，
     * 然后将指针指向新的 String 对象。这就会对程序运行产生很大的影响，因为当内存中的无引用对象多了以后，JVM的GC进程就会进行垃圾回收，这个过程会耗费很长一段时间
     *
     * 而StringBuilder和StringBuffer的对象是变量，对变量进行操作就是直接对该对象进行更改，而不进行创建和回收的操作，所以速度要比String快很多。
     */
    @Test
    public void test1() {
        String s1 = "asc";
        System.out.println(s1);
        s1 = s1 + "qw";
        System.out.println(s1);
    }
}
