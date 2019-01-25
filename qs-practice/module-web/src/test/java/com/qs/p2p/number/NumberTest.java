package com.qs.p2p.number;

import org.junit.Test;

/**
 * Created by scq on 2018-01-19 14:43:46
 */
public class NumberTest {

	@Test
	public void testNumber() {
//		int i=(int)(Math.random()*900)+100;
		int i= new java.util.Random().nextInt(900)+100;
		System.out.println(i);
	}
}
