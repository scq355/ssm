package com.qs.p2p.strategypattern.base_1;

/**
 * Created by scq on 2018-02-27 14:24:35
 */
public class Context {
	private Strategy strategy;

	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	public void contextInterface() {
		strategy.algorithmInterface();
	}
}
