package com.qs.p2p.factorymethodpattern.base_1;

/**
 * Created by scq on 2018-03-22 15:51:52
 */
public class AddFactory implements IFactory {
	@Override
	public Operation createOperation() {
		return new OperationAdd();
	}
}
