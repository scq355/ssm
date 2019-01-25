package com.qs.p2p.collections;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by scq on 2018-04-26 13:38:28
 */
public class ListTest {
	@Test
	public void testInitValue() {
		List<Boolean> userExchangeInfo = new ArrayList<>(16);
//		userExchangeInfo.replaceAll(false);
		System.out.println(JSON.toJSONString(userExchangeInfo));
	}

	@Test
	public void testArray2List() {
		String originStr = "1,2,3,4,5,6,7,8";
		String[] originStrArray = originStr.split(",");
		List<String> resList = Arrays.asList(originStrArray);
		System.out.println(JSON.toJSONString(resList));
	}

	@Test
	public void test3() {
		List<Integer> list1 = new ArrayList<>(20);
		for (int i = 0; i < 10; i++) {
			list1.add(i);
		}

		List<Integer> list2 = new ArrayList<>(list1);
		for (Integer num : list1) {
			System.out.print(num + " ");
		}
		System.out.println();
		for (Integer num : list2) {
			System.out.print(num + " ");
		}
		System.out.println();
		System.out.println(null == null);

		// indexOf()
		list1 = new ArrayList<>();
		list1.add(1);
		list1.add(2);
		list1.add(null);
		list1.add(2);
		list1.add(3);

		System.out.println("null: "+ list1.indexOf(null));
		System.out.println("2: "+ list1.indexOf(2));
		System.out.println("4: "+ list1.indexOf(4));

	}
}
