package com.qs.p2p.builderpattern.base_1;

/**
 * Created by scq on 2018-03-27 13:40:32
 */
public class ConcreteBuilder1 extends Builder {
	private Product product = new Product();

	@Override
	public void buildPartA() {
		product.add("部件A");
	}

	@Override
	public void buildPartB() {
		product.add("部件B");
	}

	@Override
	public Product getResult() {
		return product;
	}
}
