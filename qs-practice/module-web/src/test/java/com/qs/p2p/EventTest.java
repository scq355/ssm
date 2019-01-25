package com.qs.p2p;

import com.qs.p2p.model.EmailEvent;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EventTest {

    // FIXME:有问题
    @Test
    public void testEvent() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring.xml");

        EmailEvent emailEvent = applicationContext.getBean(EmailEvent.class, new EmailEvent("hello Spring!", "cxg@126.com", "This is SpringApplicatoinContext test!"));
        //主动触发事件监视机制
        applicationContext.publishEvent(emailEvent);
    }
}
