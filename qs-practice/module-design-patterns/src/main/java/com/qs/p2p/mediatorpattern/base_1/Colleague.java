package com.qs.p2p.mediatorpattern.base_1;

/**
 * Created by scq on 2018-03-23 14:46:39
 */
public abstract class Colleague {
	protected Mediator mediator;

	public Colleague(Mediator mediator) {
		this.mediator = mediator;
	}
}
