package com.qs.p2p.factorymethodpattern.base_2;

/**
 * Created by scq on 2018-03-22 16:17:46
 */
public class UndergraduateFactory implements IFactory {
	@Override
	public LeiFeng createLeiFeng() {
		return new Undergraduate();
	}
}
