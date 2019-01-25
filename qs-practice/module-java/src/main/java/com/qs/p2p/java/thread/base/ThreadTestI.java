package com.qs.p2p.java.thread.base;

/**
 * https://blog.csdn.net/evankaka/article/details/44153709#
 */
public class ThreadTestI extends Thread{

    private String name;

    public ThreadTestI(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行" + i);
            try {
                sleep((long) (Math.random()* 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadTestI threadTestI = new ThreadTestI("a");
        ThreadTestI threadTestI1 = new ThreadTestI("新线程");
        /**
         * start()方法的调用后并不是立即执行多线程代码，而是使得该线程变为可运行态（Runnable），什么时候运行是由操作系统决定的。
         */
        threadTestI.start();
        threadTestI1.start();
    }
}
