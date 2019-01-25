package com.qs.p2p.mediatorpattern.base_1;

/**
 * Created by scq on 2018-03-23 15:20:41
 */
public class Operate {
	public static void main(String[] args) {
		ConcreteMediator concreteMediator = new ConcreteMediator();
		ConcreteColleague1 concreteColleague1 = new ConcreteColleague1(concreteMediator);
		ConcreteColleague2 concreteColleague2 = new ConcreteColleague2(concreteMediator);

		concreteMediator.setConcreteColleague1(concreteColleague1);
		concreteMediator.setConcreteColleague2(concreteColleague2);

		concreteColleague1.send("吃过饭了吗?");
		concreteColleague2.send("没有呢，你打算请客?");
	}
}
