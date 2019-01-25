package com.qs.p2p.adapterpattern.base_2;

/**
 * Created by scq on 2018-03-27 14:31:33
 */
public class Operate {
	public static void main(String[] args) {
		Player playerA = new Forwards("巴蒂尔");
		playerA.attack();

		Player playerB = new Forwards("麦克格雷迪");
		playerB.attack();

		Player playerC = new Translator("姚明");
		playerC.attack();
		playerC.defense();
	}
}
