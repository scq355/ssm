package com.qs.p2p.proxypattern.base_1;

/**
 * 无代理模式
 * Created by scq on 2018-03-22 14:52:24
 */
public class Operate {
	public static void main(String[] args) {
		SchoolGirl schoolGirl = new SchoolGirl();
		schoolGirl.setName("娇娇");

		Pursuit pursuit = new Pursuit(schoolGirl);
		pursuit.giveChocolate();
		pursuit.giveDolls();
		pursuit.giveFlowers();


	}
}
