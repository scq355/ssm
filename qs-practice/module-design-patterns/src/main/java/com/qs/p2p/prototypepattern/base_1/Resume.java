package com.qs.p2p.prototypepattern.base_1;

/**
 * Created by scq on 2018-03-23 09:22:28
 */
public class Resume {
	private String name;
	private String sex;
	private Integer age;
	private String timeArea;
	private String company;

	public Resume(String name) {
		this.name = name;
	}

	public void setPersonalInfo(String sex, Integer age) {
		this.sex = sex;
		this.age = age;
	}

	public void setWorkExperience(String timeArea, String company) {
		this.timeArea = timeArea;
		this.company = company;
	}

	public void display() {
		System.out.println("个人信息：" + name + sex + age);
		System.out.println("工作经历：" + timeArea + company);
	}
}
