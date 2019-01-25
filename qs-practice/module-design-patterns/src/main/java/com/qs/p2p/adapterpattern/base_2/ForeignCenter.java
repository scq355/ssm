package com.qs.p2p.adapterpattern.base_2;

/**
 * Created by scq on 2018-03-27 14:24:14
 */
public class ForeignCenter {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void fattack() {
		System.out.println("外籍中锋" + this.name + " 进攻");
	}

	public void fback() {
		System.out.println("外籍中锋" + this.name + " 防守");
	}
}
