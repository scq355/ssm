package com.qs.p2p.java.thread.juc.part01;

/**
 * Created by scq on 2018-07-26 16:17:07
 */
public class TestCompareAndSwap {

	public static void main(String[] args) {
		final CompareAndSwap cas = new CompareAndSwap();

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				int expectedValue = cas.get();
				boolean b = cas.compareAndSet(expectedValue, (int)(Math.random() * 101));
				System.out.println(b);
			}).start();
		}

	}
}


class CompareAndSwap {
	private int value;

	//获取内存值
	public synchronized int get(){
		return value;
	}

	//比较
	public synchronized int compareAndSwap(int expectedValue, int newValue){
		int oldValue = value;

		if(oldValue == expectedValue){
			this.value = newValue;
		}

		return oldValue;
	}

	//设置
	public synchronized boolean compareAndSet(int expectedValue, int newValue){
		return expectedValue == compareAndSwap(expectedValue, newValue);
	}
}