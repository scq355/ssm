package com.qs.p2p.builderpattern.base_1;

/**
 * Created by scq on 2018-03-27 13:46:53
 */
public class Operate {
	public static void main(String[] args) {
		Director director = new Director();
		Builder builder1 = new ConcreteBuilder1();
		Builder builder2 = new ConcreteBuilder2();

		director.construct(builder1);
		Product product1 = builder1.getResult();
		product1.show();

		director.construct(builder2);
		Product product2 = builder2.getResult();
		product2.show();
	}
}
