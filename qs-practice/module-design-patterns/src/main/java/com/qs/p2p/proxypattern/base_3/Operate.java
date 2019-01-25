package com.qs.p2p.proxypattern.base_3;

import com.qs.p2p.proxypattern.base_2.SchoolGirl;

/**
 * 代理模式
 * Created by scq on 2018-03-22 15:04:56
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
