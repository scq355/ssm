package com.qs.p2p.java8.thread;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * Created by scq on 2018-07-26 15:19:58
 */
public class TestForkJoin {
	@Test
	public void test1() {
		long start = System.currentTimeMillis();

		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinCalculate(0L, 10000000000L);

		long sum = pool.invoke(task);
		System.out.println(sum);

		long end = System.currentTimeMillis();

		System.out.println("耗费的时间为: " + (end - start));
	}

	@Test
	public void test2() {
		long start = System.currentTimeMillis();

		long sum = 0L;

		for (long i = 0L; i <= 10000000000L; i++) {
			sum += i;
		}

		System.out.println(sum);

		long end = System.currentTimeMillis();

		System.out.println("耗费的时间为: " + (end - start));
	}

	@Test
	public void test3() {
		long start = System.currentTimeMillis();

		Long sum = LongStream.rangeClosed(0L, 10000000000L)
				.parallel()
				.sum();

		System.out.println(sum);

		long end = System.currentTimeMillis();

		System.out.println("耗费的时间为: " + (end - start));
	}
}
