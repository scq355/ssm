package com.qs.p2p.collections;

import java.util.Comparator;

/**
 * Created by scq on 2018-05-14 10:08:01
 */
public class DescComparator implements Comparator{
	@Override
	public int compare(Object o1, Object o2) {
		String param_1 = (String) o1;
		String param_2 = (String) o2;
		return -param_1.compareTo(param_2);
	}
}
