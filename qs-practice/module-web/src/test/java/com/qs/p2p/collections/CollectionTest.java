package com.qs.p2p.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * http://blog.csdn.net/u011240877/article/details/52802849
 * Created by scq on 2018-02-08 14:44:59
 */
public class CollectionTest {

	@Test
	public void testCollection() {
		List<Integer> list_a = new ArrayList<>();
		list_a.add(12);
		list_a.add(2);
		list_a.add(32);
		list_a.add(14);
		list_a.add(1);
		list_a.add(6);

		List<Integer> list_b = new ArrayList<>();
		list_b.add(12);
		list_b.add(2);
		list_b.add(132);
		list_b.add(214);
		list_b.add(31);
		list_b.add(46);

		System.out.println(list_a.toString());
		list_a.subList(0, 2).clear();
		System.out.println(list_a.toString());

		System.out.println("++++++++++++++++");
		System.out.println(list_b.toString());
		List<Integer> sublist_b = list_b.subList(0, 2);
		System.out.println("++++++++++++++++");
		System.out.println(sublist_b.toString() + " ");
		System.out.println("++++++++++++++++");

		sublist_b.clear();

		System.out.println(list_b.toString());


		System.out.println(list_a.retainAll(list_b));

		for (Integer num : list_a) {
			System.out.println(num);
		}

		Iterator iterator = list_a.listIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		list_b.stream().forEach(e -> System.out.println(e));

		List<String> list_d = new ArrayList<>();
		list_d.add("scq");
		list_d.add("sss");
		list_d.add("rrr");

		List<?> list_c = new ArrayList<>(list_d);

		System.out.println(list_c.toString());


	}
}
