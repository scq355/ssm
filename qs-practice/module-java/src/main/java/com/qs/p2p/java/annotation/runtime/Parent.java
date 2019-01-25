package com.qs.p2p.java.annotation.runtime;

import java.util.Arrays;

@MyAnnotation(name = "scq", value = "scq355", newNames = {"123", "234"})
public class Parent {

    public void ceate() {
        MyAnnotation myAnnotation = this.getClass().getAnnotation(MyAnnotation.class);
        if (myAnnotation != null) {
            String name = myAnnotation.name();
            String value = myAnnotation.value();
            String[] strings = myAnnotation.newNames();
            System.out.printf("name=" + name + ",value=" + value + ",newNames=" + Arrays.toString(strings));
        }
    }
}
