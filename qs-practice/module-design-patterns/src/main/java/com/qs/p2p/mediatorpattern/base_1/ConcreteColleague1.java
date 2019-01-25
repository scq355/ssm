package com.qs.p2p.mediatorpattern.base_1;

/**
 * Created by scq on 2018-03-23 15:15:19
 */
public class ConcreteColleague1 extends Colleague {
	public ConcreteColleague1(Mediator mediator) {
		super(mediator);
	}

	public void send(String message) {
		mediator.send(message, this);
	}

	public void notify(String message) {
		System.out.println("同事1收到信息：" + message);
	}
}
