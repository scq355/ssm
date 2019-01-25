package com.qs.p2p.prototypepattern.base_2;

/**
 * Created by scq on 2018-03-23 14:01:54
 */
public abstract class Prototype{
	private Integer id;

	public Prototype(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public abstract Prototype clone();
}
