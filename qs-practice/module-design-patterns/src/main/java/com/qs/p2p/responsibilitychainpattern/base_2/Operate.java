package com.qs.p2p.responsibilitychainpattern.base_2;

/**
 * 责任链模式
 * Created by scq on 2018-03-24 14:54:54
 */
public class Operate {

	public static void main(String[] args) {
		CommonManager jinli = new CommonManager("金利");
		Majordomo zongjian = new Majordomo("宗剑");
		GeneralManager zhongjingli = new GeneralManager("钟精励");
		jinli.setSuperior(zongjian);
		zongjian.setSuperior(zhongjingli);

		Request request = new Request();
		request.setRequestType("请假");
		request.setRequestContent("小菜请假");
		request.setNumber(1);
		jinli.requestApplications(request);

		Request request2 = new Request();
		request2.setRequestType("请假");
		request2.setRequestContent("小菜请假");
		request2.setNumber(4);
		jinli.requestApplications(request2);

		Request request3 = new Request();
		request3.setRequestType("加薪");
		request3.setRequestContent("小菜请求加薪");
		request3.setNumber(500);
		jinli.requestApplications(request3);

		Request request4 = new Request();
		request4.setRequestType("加薪");
		request4.setRequestContent("小菜请求加薪");
		request4.setNumber(1000);
		jinli.requestApplications(request4);
	}
}
