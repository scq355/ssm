package com.qs.p2p.proxypattern.base_3;

import com.qs.p2p.proxypattern.base_2.SchoolGirl;

/**
 * Created by scq on 2018-03-22 15:03:28
 */
public class Proxy implements GiveGift {

	private Pursuit gg;
	public Proxy(SchoolGirl mm) {
		gg = new Pursuit(mm);
	}

	@Override
	public void giveDolls() {
		gg.giveDolls();
	}

	@Override
	public void giveFlowers() {
		gg.giveFlowers();
	}

	@Override
	public void giveChocolate() {
		gg.giveChocolate();
	}
}
