package com.qs.p2p.adapterpattern.base_1;

/**
 * Created by scq on 2018-03-27 14:07:50
 */
public class Operate {
	public static void main(String[] args) {
		Target target = new Adapter();
		target.request();
	}
}
