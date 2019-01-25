package com.qs.p2p.mediatorpattern.base_1;

/**
 * Created by scq on 2018-03-23 15:17:18
 */
public class ConcreteColleague2 extends Colleague {
	public ConcreteColleague2(Mediator mediator) {
		super(mediator);
	}

	public void send(String message) {
		mediator.send(message, this);
	}

	public void notify(String message) {
		System.out.println("同事2收到信息：" + message);
	}
}
