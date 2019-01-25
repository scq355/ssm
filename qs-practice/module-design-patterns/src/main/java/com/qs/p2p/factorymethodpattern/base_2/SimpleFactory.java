package com.qs.p2p.factorymethodpattern.base_2;

/**
 * Created by scq on 2018-03-22 16:19:15
 */
public class SimpleFactory {
	public static LeiFeng CreateLeiFeng(String type)
	{
		LeiFeng result = null;
		switch (type)
		{
			case "学雷锋的大学生":
				result = new Undergraduate();
				break;
			case "社区志愿者":
				result = new Volunteer();
				break;
			default:
				break;

		}
		return result;
	}
}
