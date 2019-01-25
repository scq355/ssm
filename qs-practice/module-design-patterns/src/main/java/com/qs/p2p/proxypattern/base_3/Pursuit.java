package com.qs.p2p.proxypattern.base_3;

import com.qs.p2p.proxypattern.base_2.SchoolGirl;

/**
 * Created by scq on 2018-03-22 15:01:44
 */
public class Pursuit implements GiveGift {

	SchoolGirl mm;
	public Pursuit(SchoolGirl schoolGirl) {
		this.mm = schoolGirl;
	}

	@Override
	public void giveDolls() {
		System.out.println(mm.getName() + "送你洋娃娃");
	}

	@Override
	public void giveFlowers() {
		System.out.println(mm.getName() + "送你鲜花");
	}

	@Override
	public void giveChocolate() {
		System.out.println(mm.getName() + "送你巧克力");
	}
}
