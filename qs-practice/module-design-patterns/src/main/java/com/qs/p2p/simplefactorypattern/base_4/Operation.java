package com.qs.p2p.simplefactorypattern.base_4;

/**
 * Created by scq on 2018-02-26 10:20:16
 */
public class Operation {
	private double numberA;
	private double numberB;

	public Operation() {
	}

	public Operation(double numberA, double numberB) {
		this.numberA = numberA;
		this.numberB = numberB;
	}

	public double getNumberA() {
		return numberA;
	}

	public void setNumberA(double numberA) {
		this.numberA = numberA;
	}

	public double getNumberB() {
		return numberB;
	}

	public void setNumberB(double numberB) {
		this.numberB = numberB;
	}

	public double getResult() {
		double result = 0;
		return result;
	}
}
