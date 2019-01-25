package com.qs.p2p.aop.test2;

import com.qs.p2p.aop.Greeting;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest2 {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-service.xml");
        Greeting greeting = (Greeting) context.getBean("greetingProxy");
        greeting.say("孙常青~");
    }
}
