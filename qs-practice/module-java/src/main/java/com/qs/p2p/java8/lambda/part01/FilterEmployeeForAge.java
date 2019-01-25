package com.qs.p2p.java8.lambda.part01;

import com.qs.p2p.java8.model.Employee;

public class FilterEmployeeForAge implements MyPredicate<Employee>{

	@Override
	public boolean test(Employee t) {
		return t.getAge() <= 35;
	}

}
