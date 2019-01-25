package com.qs.p2p.java8.lambda.part01;

import com.qs.p2p.java8.model.Employee;

public class FilterEmployeeForSalary implements MyPredicate<Employee> {

	@Override
	public boolean test(Employee t) {
		return t.getSalary() >= 5000;
	}

}
