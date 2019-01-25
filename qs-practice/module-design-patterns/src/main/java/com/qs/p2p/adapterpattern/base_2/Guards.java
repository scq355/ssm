package com.qs.p2p.adapterpattern.base_2;

/**
 * Created by scq on 2018-03-27 14:23:17
 */
public class Guards extends Player {
	public Guards(String name) {
		super(name);
	}

	@Override
	public void attack() {
		System.out.println("后卫" + this.name + " 进攻");
	}

	@Override
	public void defense() {
		System.out.println("后卫" + this.name + " 防守");
	}
}
