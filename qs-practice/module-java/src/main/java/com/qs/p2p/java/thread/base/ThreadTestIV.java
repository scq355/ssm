package com.qs.p2p.java.thread.base;

/**
 * yield()
 */
public class ThreadTestIV extends Thread{
    private String name;

    public ThreadTestIV(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("" + this.getName() + "-----" + i);
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行)
            if (i ==5) {
                this.yield();
            }
        }
    }

    public static void main(String[] args) {
        ThreadTestIV th1 = new ThreadTestIV("张三");
        ThreadTestIV th2 = new ThreadTestIV("李四");
        th1.start();
        th2.start();
    }
}
