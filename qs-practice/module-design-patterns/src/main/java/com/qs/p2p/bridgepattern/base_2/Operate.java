package com.qs.p2p.bridgepattern.base_2;

/**
 * Created by scq on 2018-03-26 11:57:46
 */
public class Operate {
	public static void main(String[] args) {
		HandsetSoft handsetSoft;
		handsetSoft = new HandsetBrandMGame();
		handsetSoft.run();

		handsetSoft = new HandsetBrandNGame();
		handsetSoft.run();

		handsetSoft = new HandsetBrandMAddressList();
		handsetSoft.run();

		handsetSoft = new HandsetBrandNAddressList();
		handsetSoft.run();

	}
}
