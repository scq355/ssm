package com.qs.p2p.responsibilitychainpattern.base_1;

/**
 * Created by scq on 2018-03-24 14:27:02
 */
public class Manager {
	protected String name;

	public Manager(String name) {
		this.name = name;
	}

	public void getResult(ManagerLevel managerLevel, Request request) {
		if (managerLevel.equals(ManagerLevel.经理)) {
			if (request.getRequestType().equals("请假") && request.getNumber() <= 2) {
				System.out.println(name + request.getRequestContent() + "数量" + request.getNumber() + "被批准");
			} else {
				System.out.println(name + request.getRequestContent() + "数量" + request.getNumber() + "我无权处理");
			}
		} else if (managerLevel.equals(ManagerLevel.总监)) {
			if (request.getRequestType().equals("请假") && request.getNumber() <= 5) {
				System.out.println(name + request.getRequestContent() + "数量" + request.getNumber() + "被批准");
			} else {
				System.out.println(name + request.getRequestContent() + "数量" + request.getNumber() + "我无权处理");
			}
		} else if (managerLevel.equals(ManagerLevel.总经理)) {
			if (request.getRequestType().equals("请假")) {
				System.out.println(name + request.getRequestContent() + "数量" + request.getNumber() + "被批准");
			} else if (request.getRequestType().equals("加薪") && request.getNumber() <= 500) {
				System.out.println(name + request.getRequestContent() + "数量" + request.getNumber() + "被批准");
			} else if (request.getRequestType().equals("加薪") && request.getNumber() > 500) {
				System.out.println(name + request.getRequestContent() + "数量" + request.getNumber() + "再说吧");
			}
		}
	}
}
