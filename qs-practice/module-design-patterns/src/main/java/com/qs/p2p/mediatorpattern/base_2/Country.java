package com.qs.p2p.mediatorpattern.base_2;

/**
 * Created by scq on 2018-03-23 15:30:40
 */
public class Country {
	protected UnitedNations mediator;

	public Country(UnitedNations mediator) {
		this.mediator = mediator;
	}
}
