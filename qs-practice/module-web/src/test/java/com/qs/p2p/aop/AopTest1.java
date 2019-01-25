package com.qs.p2p.aop;


import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

public class AopTest1 {

    @Test
    public void test1() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GreetingImpl());
        proxyFactory.addAdvice(new BeforeAndAfterAdvice());
        proxyFactory.addAdvice(new AroundAdvice());

        Greeting greeting = (Greeting) proxyFactory.getProxy();
        greeting.say("scq");
    }
}
