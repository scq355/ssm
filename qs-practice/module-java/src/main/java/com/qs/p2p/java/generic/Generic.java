package com.qs.p2p.java.generic;

import java.util.List;

/**
 * 泛型
 *
 * 泛型的本质是参数化类型，即给类型指定一个参数，然后在使用时再指定此参数具体的值，
 * 那样这个类型就可以在使用时决定了。这种参数类型可以用在类、接口和方法中，分别被称为泛型类、泛型接口、泛型方法。
 *
 * 泛型类中的静态方法和静态变量不可以使用泛型类所声明的泛型类型参数
 * Created by scq on 2018-06-28 11:42:03
 */
public class Generic<T> {
	public T name;
	public  Generic(){}
	public Generic(T param) {
		name = param;
	}

	public T m(){
		return name;
	}

	public <E> void m1(E e){ }
	public <T> T m2(T e){
		return e;
	}

	public void m3(List<?> list){
		for (Object o : list) {
			System.out.println(o);
		}
	}

	public void printIntValue(List<? extends Number> list) {
		for (Number number : list) {
			System.out.print(number.intValue()+" ");
		}
	}

	public void fillNumberList(List<? super Number> list) {
		list.add(new Integer(0));
		list.add(new Float(1.0));
	}

	public static <T>T show(T one){
		return null;
	}
}
