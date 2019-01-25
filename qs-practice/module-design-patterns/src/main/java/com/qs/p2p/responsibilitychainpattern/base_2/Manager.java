package com.qs.p2p.responsibilitychainpattern.base_2;

/**
 * Created by scq on 2018-03-24 14:27:02
 */
public abstract class Manager {
	protected String name;
	//管理者的上级
	protected Manager superior;

	public Manager(String name) {
		this.name = name;
	}

	//设置管理者的上级
	public void setSuperior(Manager superior) {
		this.superior = superior;
	}

	//申请请求
	public abstract void requestApplications(Request request);
}
