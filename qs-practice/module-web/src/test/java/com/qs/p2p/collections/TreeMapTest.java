package com.qs.p2p.collections;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by scq on 2018-05-14 10:02:08
 */
public class TreeMapTest {


	/**
	 * TreeMap是SortedMap接口基于红黑树的实现，该类保证了映射按照升序排列关键字。
	 * HashMap是根据键的HashCode 值存储数据，取得数据的顺序是完全随机的，HashMap取值的速度更快。
	 */
	@Test
	public void testTreeMap() {
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("sss", "sss");
		hashMap.put("aaa", "aaa");
		hashMap.put("fff", "fff");
		hashMap.put("mmm", "mmm");
		hashMap.put("bbb", "bbb");
		hashMap.put("ggg", "ggg");
		Iterator iterator = hashMap.keySet().iterator();
		System.out.println("HashMap默认排序：");
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			System.out.println(hashMap.get(key));
		}

		Map<String, String> map = new TreeMap<>();
		map.put("sss", "sss");
		map.put("aaa", "aaa");
		map.put("fff", "fff");
		map.put("mmm", "mmm");
		map.put("bbb", "bbb");
		map.put("ggg", "ggg");
		iterator = map.keySet().iterator();
		System.out.println("默认正序排序：");
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			System.out.println(map.get(key));
		}

		DescComparator descComparator = new DescComparator();
		Map<String, String> descMap = new TreeMap<>(descComparator);
		descMap.put("sss", "sss");
		descMap.put("aaa", "aaa");
		descMap.put("fff", "fff");
		descMap.put("mmm", "mmm");
		descMap.put("bbb", "bbb");
		descMap.put("ggg", "ggg");
		iterator = descMap.keySet().iterator();
		System.out.println("自定义倒序排序：");
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			System.out.println(descMap.get(key));
		}

	}

}
