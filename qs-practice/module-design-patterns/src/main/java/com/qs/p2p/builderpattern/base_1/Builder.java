package com.qs.p2p.builderpattern.base_1;

/**
 * Created by scq on 2018-03-27 13:37:18
 */
public abstract class Builder {
	public abstract void buildPartA();
	public abstract void buildPartB();
	public abstract Product getResult();
}
