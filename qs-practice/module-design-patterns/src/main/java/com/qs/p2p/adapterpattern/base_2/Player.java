package com.qs.p2p.adapterpattern.base_2;

/**
 * Created by scq on 2018-03-27 14:17:24
 */
public abstract class Player {
	protected String name;

	public Player(String name) {
		this.name = name;
	}

	public abstract void attack();
	public abstract void defense();
}
