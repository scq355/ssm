package com.qs.p2p.strategypattern.base_1;

/**
 * Created by scq on 2018-02-27 14:22:55
 */
public class ConcreteStrategyA extends Strategy {
	@Override
	public void algorithmInterface() {
		System.out.println("算法A实现");
	}
}
