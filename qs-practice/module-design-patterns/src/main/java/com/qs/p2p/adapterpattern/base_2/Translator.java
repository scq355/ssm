package com.qs.p2p.adapterpattern.base_2;

/**
 * Created by scq on 2018-03-27 14:29:52
 */
public class Translator extends Player {
	private ForeignCenter foreignCenter = new ForeignCenter();

	public Translator(String name) {
		super(name);
		foreignCenter.setName(name);
	}

	@Override
	public void attack() {
		foreignCenter.fattack();
	}

	@Override
	public void defense() {
		foreignCenter.fback();
	}
}
