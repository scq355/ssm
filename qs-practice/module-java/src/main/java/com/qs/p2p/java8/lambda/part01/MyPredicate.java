package com.qs.p2p.java8.lambda.part01;

/**
 * Created by scq on 2018-07-26 11:11:17
 */
@FunctionalInterface
public interface MyPredicate<T> {
	public boolean test(T t);
}
