package com.qs.p2p.simplefactorypattern.base_4;

/**
 * Created by scq on 2018-02-26 10:33:01
 */
public class OperationFactory {
	public static Operation createOperation(String operate) {
		Operation operation = null;
		switch (operate) {
			case "+":
				operation = new OperationAdd();
				break;
			case "-":
				operation = new OperationSub();
				break;
			case "*":
				operation = new OperationMul();
				break;
			case "/":
				operation = new OperationDiv();
				break;
			case "sqr":
				operation = new OperationSqr();
				break;
			case "sqrt":
				operation = new OperationSqrt();
				break;
			case "+/-":
				operation = new OperationReverse();
				break;
			default:
				break;
		}
		return operation;
	}
}
