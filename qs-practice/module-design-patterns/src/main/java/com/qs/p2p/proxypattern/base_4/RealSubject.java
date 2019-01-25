package com.qs.p2p.proxypattern.base_4;

/**
 * Created by scq on 2018-03-22 15:09:28
 */
public class RealSubject extends Subject {
	@Override
	public void requestMethod() {
		System.out.println("真实请求...");
	}

}
