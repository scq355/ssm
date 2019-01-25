package com.qs.p2p.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * https://blog.csdn.net/sinat_38259539/article/details/71799078
 * https://blog.csdn.net/carson_ho/article/details/80921333
 * Created by scq on 2018-06-29 10:56:01
 */
public class Test {

	public static void main(String[] args) {
		Student user = new Student();
		System.out.println(user.getClass().getName());

		Class userClass = Student.class;
		System.out.println(userClass.getName());

		try {
			Class userClass2 = Class.forName("com.qs.p2p.java.reflection.Student");
			System.out.println(userClass2);

			System.out.println("**********************所有公有构造方法*********************************");
			Constructor[] conArray = userClass2.getConstructors();
			for (Constructor con : conArray) {
				System.out.println(con);
			}

			System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
			conArray = userClass2.getDeclaredConstructors();
			for (Constructor con : conArray) {
				System.out.println(con);
			}

			System.out.println("*****************获取公有、无参的构造方法*******************************");
			Constructor constructor = userClass2.getConstructor(null);
			System.out.println(constructor);
			Object obj = constructor.newInstance();
			Student stu = (Student)obj;

			System.out.println("******************获取私有构造方法，并调用*******************************");
			constructor = userClass2.getDeclaredConstructor(char.class);
			System.out.println(constructor);

			constructor.setAccessible(true);
			System.out.println(obj);

			System.out.println("\r\n\r\n\r\n");

			//1.获取Class对象
			Class stuClass = Class.forName("com.qs.p2p.java8.summary.java.generic.reflection.Student");
			//2.获取字段
			System.out.println("************获取所有公有的字段********************");
			Field[] fieldArray = stuClass.getFields();
			for(Field f : fieldArray){
				System.out.println(f);
			}
			System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
			fieldArray = stuClass.getDeclaredFields();
			for(Field f : fieldArray){
				System.out.println(f);
			}
			System.out.println("*************获取公有字段**并调用***********************************");
			Field f = stuClass.getField("name");
			System.out.println(f);
			//获取一个对象
			//产生Student对象--》Student stu = new Student();
			Object object = stuClass.getConstructor().newInstance();
			//为字段设置值
			//为Student对象中的name属性赋值--》stu.name = "刘德华"
			f.set(object, "刘德华");
			//验证
			Student student = (Student)object;
			System.out.println("验证姓名：" + student.name);


			System.out.println("**************获取私有字段****并调用********************************");
			f = stuClass.getDeclaredField("phoneNum");
			System.out.println(f);
			//暴力反射，解除私有限定
			f.setAccessible(true);
			f.set(object, "18888889999");
			System.out.println("验证电话：" + student);

			System.out.println("\r\n\r\n\r\n\r\n");


			//1.获取Class对象
			Class stuClass2 = Class.forName("com.qs.p2p.java.reflection.Student");
			//2.获取所有公有方法
			System.out.println("***************获取所有的”公有“方法*******************");
			stuClass2.getMethods();
			Method[] methodArray = stuClass2.getMethods();
			for(Method m : methodArray){
				System.out.println(m);
			}
			System.out.println("***************获取所有的方法，包括私有的*******************");
			methodArray = stuClass2.getDeclaredMethods();
			for(Method m : methodArray){
				System.out.println(m);
			}
			System.out.println("***************获取公有的show1()方法*******************");
			Method m = stuClass2.getMethod("show1", String.class);
			System.out.println(m);
			//实例化一个Student对象
			Object obj2 = stuClass2.getConstructor().newInstance();
			m.invoke(obj2, "刘德华");

			System.out.println("***************获取私有的show4()方法******************");
			m = stuClass2.getDeclaredMethod("show4", int.class);
			System.out.println(m);
			m.setAccessible(true);//解除私有限定
			Object result = m.invoke(obj2, 20);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
			System.out.println("返回值：" + result);


			System.out.println("\r\n\r\n\r\n\r\n");

			//1、获取Student对象的字节码
			Class clazz = Class.forName("com.qs.p2p.java.reflection.Student");

			//2、获取main方法
			Method methodMain = clazz.getMethod("main", String[].class);//第一个参数：方法名称，第二个参数：方法形参的类型，
			//3、调用main方法
//			methodMain.invoke(null, new String[]{"a","b","c"});
//			第一个参数，对象类型，因为方法是static静态的，所以为null可以，第二个参数是String数组，这里要注意在jdk1.4时是数组，jdk1.5之后是可变参数
//			这里拆的时候将  new String[]{"a","b","c"} 拆成3个对象。。。所以需要将它强转。
			methodMain.invoke(null, (Object)new String[]{"a","b","c"});//方式一
			methodMain.invoke(null, new Object[]{new String[]{"a","b","c"}});//方式二

		} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
				| IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
}
