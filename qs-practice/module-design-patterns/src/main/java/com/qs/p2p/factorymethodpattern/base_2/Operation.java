package com.qs.p2p.factorymethodpattern.base_2;

/**
 * 工厂方法模式
 * Created by scq on 2018-03-22 16:20:01
 */
public class Operation {

	public static void main(String[] args) {
		LeiFeng xueleifeng = new Undergraduate();

		xueleifeng.buyRice();
		xueleifeng.sweep();
		xueleifeng.wash();

		LeiFeng student1 = new Undergraduate();
		student1.buyRice();
		LeiFeng student2 = new Undergraduate();
		student2.sweep();
		LeiFeng student3 = new Undergraduate();
		student3.wash();

		//简单工厂模式
		LeiFeng studentA = SimpleFactory.CreateLeiFeng("学雷锋的大学生");
		studentA.buyRice();
		LeiFeng studentB = SimpleFactory.CreateLeiFeng("学雷锋的大学生");
		studentB.sweep();
		LeiFeng studentC = SimpleFactory.CreateLeiFeng("学雷锋的大学生");
		studentC.wash();

		//工厂方法模式
		IFactory factory = new UndergraduateFactory();
		LeiFeng student = factory.createLeiFeng();

		student.buyRice();
		student.sweep();
		student.wash();
	}
}
