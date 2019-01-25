package com.qs.p2p.java.thread.juc.part03;

public class TestAtomicDemo {

    private static String[] args;

    public static void main(String[] args) {
        TestAtomicDemo.args = args;
        int i = 10;
        i = i++;
//        System.out.println(i);

        int j = 10;
        j = ++j;
//        System.out.println(j);

        int k = 10;
        // 读-改-写
        int temp = k;
        k = k + 1;
        k = temp;

        AtomicDemo atomicDemo = new AtomicDemo();

        for (int l = 0; l < 10; l++) {
            new Thread(atomicDemo).start();
        }
    }
}


class AtomicDemo implements Runnable {

    private int SerialNumber = 0;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + ":" + getSerialNumber());
    }

    public int getSerialNumber() {
        return SerialNumber++;
    }
}

