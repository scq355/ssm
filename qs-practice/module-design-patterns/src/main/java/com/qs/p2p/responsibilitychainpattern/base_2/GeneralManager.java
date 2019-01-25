package com.qs.p2p.responsibilitychainpattern.base_2;

/**
 * Created by scq on 2018-03-24 14:53:17
 */
public class GeneralManager extends Manager {
	public GeneralManager(String name) {
		super(name);
	}

	@Override
	public void requestApplications(Request request) {
		if (request.getRequestType().equals("请假")) {
			System.out.println(name + request.getRequestContent() + "数量" + request.getNumber() + "被批准");
		} else if (request.getRequestType().equals("加薪") && request.getNumber() <= 500) {
			System.out.println(name + request.getRequestContent() + "数量" + request.getNumber() + "被批准");
		} else if (request.getRequestType().equals("加薪") && request.getNumber() > 500) {
			System.out.println(name + request.getRequestContent() + "数量" + request.getNumber() + "再说吧");
		}
	}
}
