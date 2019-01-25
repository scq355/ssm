package com.qs.p2p.factorymethodpattern.base_1;

/**
 * Created by scq on 2018-03-22 16:00:54
 */
public class MulFactory implements IFactory {
	@Override
	public Operation createOperation() {
		return new OperationMul();
	}
}
