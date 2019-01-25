package com.qs.p2p.java.thread.juc.part02;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 一、创建执行线程的方式三：实现 Callable 接口。 相较于实现 Runnable 接口的方式，方法可以有返回值，并且可以抛出异常。
 *
 * 二、执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。  FutureTask 是  Future 接口的实现类
 *
 * Created by scq on 2018-07-26 16:23:12
 */
public class TestCallable {
	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();

		//1.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
		FutureTask<Integer> result = new FutureTask<>(td);

		new Thread(result).start();

		//2.接收线程运算后的结果
		Integer sum = null;
		try {
			sum = result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println(sum);
		System.out.println("------------------------------------");

		ThreadDemo2 threadDemo2 = new ThreadDemo2();
		new Thread(threadDemo2).start();
	}

}

class ThreadDemo implements Callable {

	@Override
	public Integer call() throws Exception {
		int sum = 0;

		for (int i = 0; i <= 100000; i++) {
			sum += i;
		}

		return sum;
	}
}

class ThreadDemo2 implements Runnable {

	@Override
	public void run() {
		int sum = 0;

		for (int i = 0; i <= 100000; i++) {
			sum += i;
		}

		System.out.println(sum);
	}
}
