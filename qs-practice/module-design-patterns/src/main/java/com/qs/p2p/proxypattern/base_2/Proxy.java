package com.qs.p2p.proxypattern.base_2;

/**
 * Created by scq on 2018-03-22 14:55:12
 */
public class Proxy {

	private SchoolGirl mm;

	public Proxy(SchoolGirl schoolGirl) {
		this.mm = schoolGirl;
	}

	public void giveDolls() {
		System.out.println(mm.getName() + "送你洋娃娃");
	}

	public void giveFlowers() {
		System.out.println(mm.getName() + "送你鲜花");
	}

	public void giveChocolate() {
		System.out.println(mm.getName() + "送你巧克力");
	}
}
