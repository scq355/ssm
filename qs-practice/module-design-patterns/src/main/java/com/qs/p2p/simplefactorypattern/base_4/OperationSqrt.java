package com.qs.p2p.simplefactorypattern.base_4;

/**
 * Created by scq on 2018-02-26 10:31:10
 */
public class OperationSqrt extends Operation {
	@Override
	public double getResult() {
		try {
			return Math.sqrt(this.getNumberB());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
