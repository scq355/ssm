package com.qs.p2p.simplefactorypattern.base_4;

/**
 * Created by scq on 2018-02-26 10:27:32
 */
public class OperationMul extends Operation {
	@Override
	public double getResult() {
		return this.getNumberA() * this.getNumberB();
	}
}
