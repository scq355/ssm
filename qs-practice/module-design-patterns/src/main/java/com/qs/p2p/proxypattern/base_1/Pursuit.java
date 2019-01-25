package com.qs.p2p.proxypattern.base_1;

/**
 * Created by scq on 2018-03-22 14:48:44
 */
public class Pursuit {
	private SchoolGirl mm;

	public Pursuit(SchoolGirl schoolGirl) {
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
