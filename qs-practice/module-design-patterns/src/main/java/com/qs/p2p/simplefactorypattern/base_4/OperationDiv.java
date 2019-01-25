package com.qs.p2p.simplefactorypattern.base_4;

/**
 * Created by scq on 2018-02-26 10:28:14
 */
public class OperationDiv extends Operation {
	@Override
	public double getResult() {
		try {
			return this.getNumberA() * this.getNumberB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
