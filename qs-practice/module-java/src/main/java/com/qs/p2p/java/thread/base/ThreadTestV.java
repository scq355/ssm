package com.qs.p2p.java.thread.base;

public class ThreadTestV implements Runnable {

    private String name;
    private Object prev;
    private Object self;

    private ThreadTestV(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name + " ");
                    count--;
                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        ThreadTestV ta = new ThreadTestV("A", c, a);
        ThreadTestV tb = new ThreadTestV("B", a, b);
        ThreadTestV tc = new ThreadTestV("C", b, c);

        new Thread(ta).start();
        Thread.sleep(100L);
        new Thread(tb).start();
        Thread.sleep(100L);
        new Thread(tc).start();
        Thread.sleep(100L);
    }
}
