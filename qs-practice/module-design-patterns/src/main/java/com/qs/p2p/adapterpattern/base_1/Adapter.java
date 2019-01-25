package com.qs.p2p.adapterpattern.base_1;

/**
 * Created by scq on 2018-03-27 14:06:43
 */
public class Adapter extends Target {
	private Adaptee adaptee = new Adaptee();

	@Override
	public void request() {
		adaptee.specificRequest();
	}
}
