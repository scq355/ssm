package com.qs.p2p.event;

import com.qs.p2p.model.EmailEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class EmailNotifier implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof EmailEvent) {
            EmailEvent emailEvent = (EmailEvent) applicationEvent;
            System.out.println(emailEvent.toString());
        } else {
            System.out.println("the Spring's event:" + applicationEvent);
        }
    }
}
