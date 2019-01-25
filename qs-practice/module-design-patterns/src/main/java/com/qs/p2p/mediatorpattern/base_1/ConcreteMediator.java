package com.qs.p2p.mediatorpattern.base_1;

/**
 * Created by scq on 2018-03-23 15:14:57
 */
public class ConcreteMediator extends Mediator {

	private ConcreteColleague1 concreteColleague1;
	private ConcreteColleague2 concreteColleague2;

	public void setConcreteColleague1(ConcreteColleague1 concreteColleague1) {
		this.concreteColleague1 = concreteColleague1;
	}

	public void setConcreteColleague2(ConcreteColleague2 concreteColleague2) {
		this.concreteColleague2 = concreteColleague2;
	}

	@Override
	public void send(String message, Colleague colleague) {
		if (colleague == concreteColleague1) {
			concreteColleague2.notify(message);
		} else if (colleague == concreteColleague2) {
			concreteColleague1.notify(message);
		}
	}
}
