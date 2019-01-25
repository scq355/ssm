package com.qs.p2p.simplefactorypattern.base_4;

/**
 * Created by scq on 2018-02-26 10:30:21
 */
public class OperationSqr extends Operation {
	@Override
	public double getResult() {
		return this.getNumberB() * this.getNumberB();
	}
}
