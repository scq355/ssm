package com.qs.p2p.factorymethodpattern;

import com.qs.p2p.factorymethodpattern.base_1.AddFactory;
import com.qs.p2p.factorymethodpattern.base_1.IFactory;
import com.qs.p2p.factorymethodpattern.base_1.Operation;
import org.junit.Test;

/**
 * 工厂方法实测
 * Created by scq on 2018-03-22 16:08:56
 */
public class FactorymethodpatternTest {

	@Test
	public void testFactorymethodpattern() {
		IFactory operFactory = new AddFactory();
		Operation operation = operFactory.createOperation();
		operation.setNumberA(12.3);
		operation.setNumberB(23.4);
		Double result = operation.getResult();
		System.out.println(result);
	}
}
