package com.qs.p2p.builderpattern.base_1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scq on 2018-03-27 13:34:22
 */
public class Product {
	List<String> partList = new ArrayList<>();

	public void add(String part) {
		partList.add(part);
	}

	public void show() {
		System.out.println("产品 创建 ----");
		for (String part : partList) {
			System.out.println(part);
		}
	}
}
