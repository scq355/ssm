package com.qs.p2p.service.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component(value = "AroundAdvice")
public class AroundAdvice implements MethodInterceptor {

    private void before() {
        System.out.println("Around-Before");
    }

    private void after() {
        System.out.println("Around-After");
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        before();
        Object result = methodInvocation.proceed();
        after();
        return result;
    }
}
