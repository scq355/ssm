package com.qs.p2p.responsibilitychainpattern.base_1;

/**
 * Created by scq on 2018-03-24 14:39:59
 */
public class Operate {

	public static void main(String[] args) {
		Manager jinli = new Manager("金利");
		Manager zongjian = new Manager("宗剑");
		Manager zhongjingli = new Manager("钟精励");

		Request request = new Request();
		request.setNumber(1000);
		request.setRequestContent("小菜请求加薪");
		request.setRequestType("加薪");

		jinli.getResult(ManagerLevel.经理, request);
		zongjian.getResult(ManagerLevel.总监, request);
		zhongjingli.getResult(ManagerLevel.总经理, request);

		Request request2 = new Request();
		request2.setRequestType("请假");
		request2.setRequestContent("小菜请假");
		request2.setNumber(3);

		jinli.getResult(ManagerLevel.经理, request2);
		zongjian.getResult(ManagerLevel.总监, request2);
		zhongjingli.getResult(ManagerLevel.总经理, request2);
	}
}
