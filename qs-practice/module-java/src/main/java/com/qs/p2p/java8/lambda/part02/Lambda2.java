package com.qs.p2p.java8.lambda.part02;

import com.qs.p2p.java8.model.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * lambda语法：
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 *
 * 1. 对象的引用 :: 实例方法名
 *
 * 2. 类名 :: 静态方法名
 *
 * 3. 类名 :: 实例方法名
 *
 * 注意：
 * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 *
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 *
 * 1. 类名 :: new
 *
 * 三、数组引用
 *
 * 	类型[] :: new;
 *
 * Created by scq on 2018-07-26 11:26:25
 */
public class Lambda2 {

	@Test
	public void test1() {
		PrintStream printStream = System.out;
		Consumer<String> consumer1 = str -> printStream.println(str);
		consumer1.accept("Hello World!");

		System.out.println("--------------------------------");

		Consumer<String> consumer2 = printStream::println;
		consumer2.accept("Hello Java8!");

		Consumer<String> consumer3 = System.out::println;
		consumer2.accept("Hello Java8!");
	}

	//对象的引用 :: 实例方法名
	@Test
	public void test2() {
		Employee emp = new Employee(101, "张三", 18, 9999.99);
		Supplier<String> stringSupplier1 = () -> emp.getName();
		System.out.println(stringSupplier1.get());

		System.out.println("----------------------------------");

		Supplier<String> stringSupplier2 = emp::getName;
		System.out.println(stringSupplier2.get());
	}

	@Test
	public void test3() {
		BiFunction<Double, Double, Double> function1 = (x, y) -> Math.max(x, y);
		System.out.println(function1.apply(1.5, 2.22));

		System.out.println("----------------------------------");

		BiFunction<Double, Double, Double> function2 = Math::max;
		System.out.println(function2.apply(1.5, 2.22));
	}

	//类名 :: 静态方法名
	@Test
	public void test4() {
		Comparator<Integer> comparator1 = (x, y) ->  Integer.compare(x, y);
		System.out.println(comparator1);

		System.out.println("-------------------------------------");

		Comparator<Integer> comparator2 = Integer::compare;
		System.out.println(comparator2);
	}

	//类名 :: 实例方法名
	@Test
	public void test5() {
		BiPredicate<String, String> biPredicate1 = (x, y) -> x.equals(y);
		System.out.println(biPredicate1.test("abcde", "abcde"));

		System.out.println("-----------------------------------------");

		BiPredicate<String, String> biPredicate2 = String::equals;
		System.out.println(biPredicate2.test("abcde", "abcde"));

		System.out.println("-----------------------------------------");

		Function<Employee, String> function1 = e -> e.show();
		System.out.println(function1.apply(new Employee()));

		System.out.println("-----------------------------------------");

		Function<Employee, String> function2 = Employee::show;
		System.out.println(function2.apply(new Employee()));
	}

	@Test
	public void test6() {
		Supplier<Employee> employeeSupplier2 = () -> new Employee();
		System.out.println(employeeSupplier2.get());

		System.out.println("-----------------------------------------");

		Supplier<Employee> employeeSupplier1 = Employee::new;
		System.out.println(employeeSupplier1.get());
	}

	//构造器引用
	@Test
	public void test7() {
		Function<String, Employee> function = Employee::new;
		System.out.println(function);

		BiFunction<String, Integer, Employee> employeeBiFunction = Employee::new;
		System.out.println(employeeBiFunction);
	}

	//数组引用
	@Test
	public void test8() {
		Function<Integer, String[]> function = (arg) -> new String[arg];
		String[] args = function.apply(10);
		System.out.println(args.length);

		System.out.println("--------------------------");

		Function<Integer, Employee[]> fun2 = Employee[] :: new;
		Employee[] emps = fun2.apply(20);
		System.out.println(emps.length);
	}
}
