package com.qs.p2p.builderpattern.base_1;

/**
 * Created by scq on 2018-03-27 13:42:47
 */
public class ConcreteBuilder2 extends Builder {
	private Product product = new Product();

	@Override
	public void buildPartA() {
		product.add("部件X");
	}

	@Override
	public void buildPartB() {
		product.add("部件Y");
	}

	@Override
	public Product getResult() {
		return product;
	}
}