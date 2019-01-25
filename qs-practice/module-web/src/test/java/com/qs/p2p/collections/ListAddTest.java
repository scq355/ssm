package com.qs.p2p.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scq on 2018-03-13 17:08:44
 */
public class ListAddTest {

	@Test
	public void testAddAll() {
		List<Integer> list_a = new ArrayList<>();
		list_a.add(1);
		list_a.add(2);
		list_a.add(3);
		list_a.add(4);
		list_a.add(5);

		List<Integer> list_b = new ArrayList<>();
		list_b.add(10);
		list_b.add(20);
		list_b.add(30);
		list_b.add(40);
		list_b.add(50);


		List<Integer> list_c = new ArrayList<>();
		list_c.addAll(list_a);
		list_c.addAll(list_b);

		System.out.printf(list_c.toString());
	}
}
