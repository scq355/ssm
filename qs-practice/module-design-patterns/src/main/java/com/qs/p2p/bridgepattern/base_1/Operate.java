package com.qs.p2p.bridgepattern.base_1;

/**
 * 桥接模式
 * Created by scq on 2018-03-26 11:39:18
 */
public class Operate {
	public static void main(String[] args) {
		Abstraction abstraction = new RefinedAbstraction();

		abstraction.setImplementor(new ConcreteImplementorA());
		abstraction.operation();

		abstraction.setImplementor(new ConcreteImplementorB());
		abstraction.operation();
	}
}
