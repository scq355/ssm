package com.qs.p2p.mediatorpattern.base_1;

/**
 * Created by scq on 2018-03-23 14:45:56
 */
public abstract class Mediator {
	public abstract void send(String message, Colleague colleague);
}
