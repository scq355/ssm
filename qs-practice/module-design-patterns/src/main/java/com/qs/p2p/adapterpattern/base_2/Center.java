package com.qs.p2p.adapterpattern.base_2;

/**
 * Created by scq on 2018-03-27 14:21:19
 */
public class Center extends Player {
	public Center(String name) {
		super(name);
	}

	@Override
	public void attack() {
		System.out.println("中锋" + this.name + "进攻");
	}

	@Override
	public void defense() {
		System.out.println("中锋" + this.name + "防守");
	}
}
