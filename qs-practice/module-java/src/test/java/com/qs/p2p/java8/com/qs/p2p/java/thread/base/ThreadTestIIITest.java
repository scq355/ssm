package com.qs.p2p.java8.com.qs.p2p.java.thread.base;

import com.qs.p2p.java.thread.base.ThreadTestIII;
import org.junit.Test;

public class ThreadTestIIITest {

    @Test
    public void test01() {
        System.out.println(Thread.currentThread().getName()+"主线程运行开始!");
        ThreadTestIII th1 = new ThreadTestIII("A");
        ThreadTestIII th2 = new ThreadTestIII("B");
        th1.start();
        th2.start();
        System.out.println(Thread.currentThread().getName()+ "主线程运行结束!");
    }

    @Test
    public void test02() {
        System.out.println(Thread.currentThread().getName()+"主线程运行开始!");
        ThreadTestIII th1 = new ThreadTestIII("A");
        ThreadTestIII th2 = new ThreadTestIII("B");
        th1.start();
        th2.start();
        try {
            th1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ "主线程运行结束!");
    }

}
