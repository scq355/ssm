package com.qs.p2p.mediatorpattern.base_2;

/**
 * Created by scq on 2018-03-23 15:35:27
 */
public class Iraq extends Country {
	public Iraq(UnitedNations mediator) {
		super(mediator);
	}

	//声明
	public void declare(String message) {
		mediator.declare(message, this);
	}
	//获得消息
	public void getMessage(String message) {
		System.out.println("伊拉克获得对方信息：" + message);
	}
}
