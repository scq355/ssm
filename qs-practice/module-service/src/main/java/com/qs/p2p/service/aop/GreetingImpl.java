package com.qs.p2p.service.aop;

import org.springframework.stereotype.Component;

@Component(value = "GreetingImpl")
public class GreetingImpl implements Greeting {
    @Override
    public void say(String name) {
        System.out.println("你好," + name);
    }
}
