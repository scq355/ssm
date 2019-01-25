package com.qs.p2p.responsibilitychainpattern.base_2;

/**
 * Created by scq on 2018-03-24 14:47:53
 */
public class CommonManager extends Manager {
	public CommonManager(String name) {
		super(name);
	}

	@Override
	public void requestApplications(Request request) {
		if (request.getRequestType().equals("请假") && request.getNumber() <= 2) {
			System.out.println(name + request.getRequestContent() + "数量" + request.getNumber() + "被批准");
		} else {
			if (superior != null) {
				superior.requestApplications(request);
			}
		}
	}
}
