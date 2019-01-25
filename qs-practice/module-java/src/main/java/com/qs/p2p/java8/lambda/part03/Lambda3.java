package com.qs.p2p.java8.lambda.part03;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *
 * Java8 内置的四大核心函数式接口
 *
 * Consumer<T> : 消费型接口
 * 		void accept(T t);
 *
 * Supplier<T> : 供给型接口
 * 		T get();
 *
 * Function<T, R> : 函数型接口
 * 		R apply(T t);
 *
 * Predicate<T> : 断言型接口
 * 		boolean test(T t);
 *
 * Created by scq on 2018-07-26 11:50:14
 */
public class Lambda3 {

	//Consumer<T> 消费型接口 :
	@Test
	public void test1() {
		happy(1000.00, m -> System.out.println(m));
	}

	public void happy(Double money, Consumer<Double> consumer) {
		consumer.accept(money);
	}


	//Supplier<T> 供给型接口 :
	@Test
	public void test2() {
		List<Integer> list = getNumberList(10, () -> (int) (Math.random() * 100));

		for (Integer num : list) {
			System.out.println(num);
		}
	}

	//需求：产生指定个数的整数，并放入集合中
	public List<Integer> getNumberList(Integer num, Supplier<Integer> supplier) {
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < num; i++) {
			Integer n = supplier.get();
			list.add(n);
		}
		return list;
	}


	//Function<T, R> 函数型接口：
	@Test
	public void test3() {
		String newStr = stringHandler("\t\t\t 你好吗，今天天气不错   ", (str) -> str.trim());
		System.out.println(newStr);

		String subString = stringHandler("\t\t\t 你好吗，今天天气不错   ", str -> str.substring(2,5));
		System.out.println(subString);
	}


	//需求：用于处理字符串
	public String stringHandler(String str, Function<String, String> function) {
		return function.apply(str);
	}


	@Test
	public void test4() {
		List<String> list = Arrays.asList("Hello", "atguigu", "Lambda", "www", "ok");
		List<String> stringList = filterStr(list, s -> s.length() > 3);
		for (String str : stringList) {
			System.out.println(str);
		}
	}

	//需求：将满足条件的字符串，放入集合中
	public List<String> filterStr(List<String> list, Predicate<String> predicate) {
		List<String> stringList = new ArrayList<>();
		for (String str : stringList) {
			if (predicate.test(str)) {
				list.add(str);
			}
		}
		return list;
	}


}
