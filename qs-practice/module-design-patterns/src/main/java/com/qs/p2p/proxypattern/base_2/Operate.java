package com.qs.p2p.proxypattern.base_2;

/**
 * 只有代理操作
 * Created by scq on 2018-03-22 14:56:29
 */
public class Operate {
	public static void main(String[] args) {
		SchoolGirl schoolGirl = new SchoolGirl();
		schoolGirl.setName("娇娇");

		Proxy proxy = new Proxy(schoolGirl);
		proxy.giveChocolate();
		proxy.giveDolls();
		proxy.giveFlowers();

	}
}
