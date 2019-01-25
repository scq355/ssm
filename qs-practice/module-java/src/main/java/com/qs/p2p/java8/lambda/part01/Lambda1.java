package com.qs.p2p.java8.lambda.part01;

import com.qs.p2p.java8.model.Employee;
import org.junit.Test;

import java.util.*;

/**
 * Created by scq on 2018-07-26 11:01:35
 */
public class Lambda1 {

	List<Employee> emps = Arrays.asList(
			new Employee(101, "张三", 18, 9999.99),
			new Employee(102, "李四", 59, 6666.66),
			new Employee(103, "王五", 28, 3333.33),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(105, "田七", 38, 5555.55)
	);

	// 原匿名内部类
	@Test
	public void test1() {
		Comparator<String> comparator = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return Integer.compare(o1.length(), o2.length());
			}
		};

		TreeSet<String> treeSet1 = new TreeSet<>(comparator);

		TreeSet<String> treeSet2 = new TreeSet<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return Integer.compare(o1.length(), o2.length());
			}
		});
	}

	@Test
	public void test2() {
		Comparator<String> comparator = (x, y) -> Integer.compare(x.length(), y.length());
		TreeSet<String> treeSet = new TreeSet<>(comparator);
	}

	//需求：获取公司中年龄小于 35 的员工信息
	public List<Employee> filterEmployeeAge(List<Employee> employees) {
		List<Employee> list = new ArrayList<>();
		for (Employee emp : emps) {
			if(emp.getAge() <= 35){
				list.add(emp);
			}
		}
		return list;
	}

	@Test
	public void test3() {
		List<Employee> employees = filterEmployeeAge(emps);

		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}

	//需求：获取公司中工资大于 5000 的员工信息
	public List<Employee> filterEmployeeSalary(List<Employee> emps){
		List<Employee> list = new ArrayList<>();

		for (Employee emp : emps) {
			if(emp.getSalary() >= 5000){
				list.add(emp);
			}
		}

		return list;
	}

	//优化方式一：策略设计模式
	public List<Employee> filterEmployee(List<Employee> employees, MyPredicate<Employee> myPredicate) {
		List<Employee> list = new ArrayList<>();
		for (Employee employee : employees) {
			if (myPredicate.test(employee)) {
				list.add(employee);
			}
		}
		return list;
	}

	@Test
	public void test4() {
		List<Employee> list = filterEmployee(emps, new FilterEmployeeForAge());

		for (Employee employee : list) {
			System.out.println(employee);
		}

		System.out.println("------------------------------------------");

		List<Employee> list2 = filterEmployee(emps, new FilterEmployeeForSalary());
		for (Employee employee : list2) {
			System.out.println(employee);
		}
	}

	//优化方式二：匿名内部类
	@Test
	public void test5() {
		List<Employee> list = filterEmployee(emps, new MyPredicate<Employee>() {
			@Override
			public boolean test(Employee employee) {
				return employee.getId() <= 103;
			}
		});
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}

	//优化方式三：Lambda 表达式
	@Test
	public void test6() {
		List<Employee> list = filterEmployee(emps, e -> e.getAge() <= 35);
		list.forEach(System.out::println);

		System.out.println("------------------------------------------");

		List<Employee> employees = filterEmployee(emps, e -> e.getSalary() >= 5000);
		employees.forEach(System.out::println);
	}

	//优化方式四：Stream API
	@Test
	public void test7() {
		emps.stream()
				.filter(e -> e.getAge() <= 35)
				.forEach(System.out::println);

		System.out.println("----------------------------------------------");

		emps.stream()
				.filter(e -> e.getSalary() >= 5000)
				.forEach(System.out::println);
	}

}
