package com.qs.p2p.java8.date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by scq on 2018-07-26 14:53:57
 */
public class TestSimpleDateFormat {

	/**
	 * 多线程安全问题
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Test
	public void test1() throws ExecutionException, InterruptedException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Callable<Date> task = new Callable<Date>() {
			@Override
			public Date call() throws Exception {
				return simpleDateFormat.parse("20161121");
			}
		};

		ExecutorService pool = Executors.newFixedThreadPool(10);

		List<Future<Date>> futures = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			futures.add(pool.submit(task));
		}

		for (Future<Date> future : futures) {
			System.out.println(future.get());
		}
		pool.shutdown();
	}

	/**
	 * 线程安全的日期格式化类
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Test
	public void test2() throws ExecutionException, InterruptedException {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		Callable<LocalDate> task = new Callable<LocalDate>() {
			@Override
			public LocalDate call() throws Exception {
				LocalDate localDate = LocalDate.parse("20161121", dateTimeFormatter);
				return localDate;
			}
		};

		ExecutorService pool = Executors.newFixedThreadPool(10);
		List<Future<LocalDate>> futures = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			futures.add(pool.submit(task));
		}

		for (Future<LocalDate> future : futures) {
			System.out.println(future.get());
		}
		pool.shutdown();
	}
}
