package com.qs.p2p;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface UserService{
    String getUserName();
}

class UserServiceImpl implements UserService {

    @Override
    public String getUserName() {
        System.out.println("UserServiceImpl getUserName");
        return null;
    }
}

interface LoginService{
    boolean checkUser();
}

class LoginServiceImpl implements LoginService{
    @Override
    public boolean checkUser() {
        System.out.println("LoginServiceImpl  checkUser");
        return false;
    }
}


/**
 * JDK动态代理
 */
class ProxyHandlers implements InvocationHandler {
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("*********代理方法执行前************");
        Object object = method.invoke(target, args);
        System.out.println("*********代理方法执行后************");
        return object;
    }
}

/**
 * CGLib动态代理
 */
class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object proxy, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        System.out.println("*********代理方法执行前************");
        Object object = methodProxy.invokeSuper(proxy, params);
        System.out.println("*********代理方法执行后************");
        return object;
    }

    //返回目标对象的代理对象
    public Object newProxy(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        enhancer.setClassLoader(target.getClass().getClassLoader());
        return enhancer.create();
    }
}

public class ProxyHandlerTest {

    @Test
    public void testJavaProxy() {
       UserService userService = new UserServiceImpl();
       LoginService loginService = new LoginServiceImpl();

       ProxyHandlers proxyHandlers = new ProxyHandlers();

       proxyHandlers.setTarget(loginService);
       LoginService loginService1 = (LoginService) Proxy.newProxyInstance(loginService.getClass().getClassLoader(), loginService.getClass().getInterfaces(), proxyHandlers);
       loginService1.checkUser();

       proxyHandlers.setTarget(userService);
       UserService userService1 = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(), proxyHandlers);
       userService1.getUserName();
    }


    @Test
    public void testCglibProxy() {
        LoginService logninService = new LoginServiceImpl();
        UserService userService = new UserServiceImpl();
        CglibProxy proxy = new CglibProxy();

        LoginService loginService1 = (LoginService) proxy.newProxy(logninService);
        UserService userService1 = (UserService) proxy.newProxy(userService);

        userService1.getUserName();
        loginService1.checkUser();
    }

    @Test
    public void testEfficiency() {
        // https://blog.csdn.net/bigtree_3721/article/details/50833812
    }
}
