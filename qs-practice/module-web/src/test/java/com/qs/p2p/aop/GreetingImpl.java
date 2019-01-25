package com.qs.p2p.aop;

public class GreetingImpl implements Greeting {
    @Override
    public void say(String name) {
        System.out.println("你好," + name);
    }
}
