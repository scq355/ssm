package com.qs.p2p.bridgepattern.base_1;

/**
 * Created by scq on 2018-03-26 11:34:34
 */
public class Abstraction {
	protected Implementor implementor;

	public void setImplementor(Implementor implementor) {
		this.implementor = implementor;
	}

	public void operation() {
		implementor.operation();
	}
}
