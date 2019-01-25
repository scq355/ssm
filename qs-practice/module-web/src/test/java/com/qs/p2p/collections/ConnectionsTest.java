package com.qs.p2p.collections;

import org.junit.Test;

import java.util.*;

/**
 * Created by scq on 2018-01-11 15:41:28
 */
public class ConnectionsTest {

	@Test
	public void testSort() {
		List<String> names1 = new ArrayList<>();
		names1.add("Google ");
		names1.add("renren ");
		names1.add("Taobao ");
		names1.add("Baidu ");
		names1.add("Sina ");

		List<String> names2 = new ArrayList<>();
		names2.add("Google ");
		names2.add("renren ");
		names2.add("Taobao ");
		names2.add("Baidu ");
		names2.add("Sina ");

		System.out.println("使用 Java 7 语法: ");

		sortUsingJava7(names1);
		System.out.println(names1);
		System.out.println("使用 Java 8 语法: ");

		sortUsingJava8(names2);
		System.out.println(names2);
	}

	// 使用 java 7 排序
	private void sortUsingJava7(List<String> names){
		Collections.sort(names, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		});
	}

	// 使用 java 8 排序
	private void sortUsingJava8(List<String> names){
		Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
	}

	@Test
	public void testList() {
		List<Integer> arrayList = new ArrayList<>();
		List<String> linkedList = new LinkedList<>();

//		String nullStr = null;
//
//		Map<String, String> map = new HashMap<>();
//
//		map.put(nullStr, nullStr);
//		map.put(nullStr, nullStr);

//		map.put(null, null);
//		map.put(null, null);

//		System.out.println(map + " " + map.size());

//		arrayList.add(nullStr);
//		arrayList.add(nullStr);

//		arrayList.add(null);
//		arrayList.add(null);

		for (int i = 0; i < 20; i++) {
			arrayList.add((int)(Math.random() * 100));
		}

		Integer[] intArray = new Integer[arrayList.size()];

		arrayList.toArray(intArray);

		Collections.sort(arrayList);

		System.out.println(arrayList.toString());

		Set<Integer> set = new HashSet<>();
		for (Integer elem : arrayList) {
			set.add(elem);
		}

		System.out.println(set.toString());

		List<Integer> subList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			subList.add(i);
		}
		System.out.println(subList);

		arrayList.addAll(subList);

		System.out.println(arrayList);

		arrayList.removeAll(subList);
		System.out.println(arrayList);

		ListIterator iterator = arrayList.listIterator();
		while (iterator.hasNext()) {
			Integer entity = (Integer) iterator.next();
			System.out.println(entity + "==");
		}

		Hashtable hashtable = new Hashtable();


//		System.out.println(arrayList + " " + arrayList.size());
	}
}
