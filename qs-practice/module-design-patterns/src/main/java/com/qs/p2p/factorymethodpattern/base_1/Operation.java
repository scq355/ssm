package com.qs.p2p.factorymethodpattern.base_1;

/**
 * Created by scq on 2018-03-22 15:49:05
 */
public class Operation {
	private Double numberA;
	private Double numberB;

	public Double getNumberA() {
		return numberA;
	}

	public Double getNumberB() {
		return numberB;
	}

	public void setNumberA(Double numberA) {
		this.numberA = numberA;
	}

	public void setNumberB(Double numberB) {
		this.numberB = numberB;
	}

	public Double getResult() {
		Double result = 0.0;
		return result;
	}
}
