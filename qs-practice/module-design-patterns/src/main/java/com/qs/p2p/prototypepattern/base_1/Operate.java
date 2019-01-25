package com.qs.p2p.prototypepattern.base_1;

/**
 * 原型模式
 * Created by scq on 2018-03-23 09:30:38
 */
public class Operate {
	public static void main(String[] args) {
		Resume a = new Resume("大鸟");
		a.setPersonalInfo("男", 29);
		a.setWorkExperience("1998-2000", " XX公司");
		a.display();

		Resume b = new Resume("大鸟");
		b.setPersonalInfo("男", 29);
		b.setWorkExperience("1998-2000", " XX公司");
		b.display();

		Resume c = new Resume("大鸟");
		c.setPersonalInfo("男", 29);
		c.setWorkExperience("1998-2000", " XX公司");
		c.display();
	}
}
