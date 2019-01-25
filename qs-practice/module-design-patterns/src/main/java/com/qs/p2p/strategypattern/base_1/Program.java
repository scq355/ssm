package com.qs.p2p.strategypattern.base_1;

/**
 * Created by scq on 2018-02-27 14:26:40
 */
public class Program {
	public static void main(String[] args) {
		Context context;

		context = new Context(new ConcreteStrategyA());
		context.contextInterface();

		context = new Context(new ConcreteStrategyB());
		context.contextInterface();

	}
}
