package com.qs.p2p.collections;

/**
 * Created by scq on 2018-03-13 14:51:02
 */
public class Student {
	private int age;
	private Boolean isUsable;

	public int getAge() {
		return age;
	}

	public Boolean isUsable() {
		return isUsable;
	}

	public void setUsable(Boolean isUsable) {
		this.isUsable = isUsable;
	}

	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return getAge()+"";
	}
}
