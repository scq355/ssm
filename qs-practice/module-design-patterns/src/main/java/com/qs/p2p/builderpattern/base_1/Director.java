package com.qs.p2p.builderpattern.base_1;

/**
 * Created by scq on 2018-03-27 13:43:44
 */
public class Director {
	public void construct(Builder builder) {
		builder.buildPartA();
		builder.buildPartB();
	}
}
