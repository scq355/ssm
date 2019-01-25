package com.qs.p2p.java.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 引入泛型目的：将类型检查工作提前到编译时期，将类型强转（cast）工作交给编译器，
 * 从而在编译时期就获得类型转换异常以及去掉源码中的类型强转代码
 * Created by scq on 2018-06-28 10:18:15
 */
public class Test {

	public static void main(String[] args) {
		Test.genericTest();
		Test.genericClass();

		Generic<String> str=new Generic<>("总有刁民想害朕");
		System.out.println(str.m());

		Integer num = str.m2(123);
		System.out.println(num);

		str.m3(Arrays.asList(1,2,3));
		str.m3(Arrays.asList("总有刁民","想害","朕"));

		/**
		 * Java编译器编译泛型的步骤：
		 1.检查泛型的类型 ，获得目标类型
		 2.擦除类型变量，并替换为限定类型（T为无限定的类型变量，用Object替换）
		 3.调用相关函数，并将结果强制转换为目标类型。
		 */
		ArrayList<String> arrayString=new ArrayList<>();
		ArrayList<Integer> arrayInteger=new ArrayList<>();
		System.out.println(arrayString.getClass()==arrayInteger.getClass());
	}

	private static void genericTest() {
		List arrayList = new ArrayList();
		arrayList.add("总有刁民想害朕");
		arrayList.add(7);

		for (Object item : arrayList) {
			if (item instanceof String) {
				String str = (String) item;
				System.out.println("泛型测试 item = " + str);
			} else if (item instanceof Integer) {
				Integer inte = (Integer) item;
				System.out.println("泛型测试 item = " + inte);
			}
		}
	}

	private static void genericClass() {
		Generic<String> str=new Generic<>("总有刁民想害朕");
		Generic<Integer> integer=new Generic<>(110);
		Generic<Boolean> b=new Generic<>(true);

		System.out.println("传入类型："+str.name+"  "+integer.name+"  "+b.name);
	}

}
