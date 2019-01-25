package com.qs.p2p.proxypattern.base_4;

/**
 * 代理类
 * Created by scq on 2018-03-22 15:10:15
 */
public class Proxy extends Subject {
	RealSubject realSubject;

	@Override
	public void requestMethod() {
		if (realSubject == null) {
			realSubject = new RealSubject();
		}
		realSubject.requestMethod();
	}

}
