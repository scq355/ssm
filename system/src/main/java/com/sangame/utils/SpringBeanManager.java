package com.sangame.utils;

import org.springframework.context.ApplicationContext;

/**
 * Created by admin on 2017/5/11.
 */
public class SpringBeanManager {

    private static ApplicationContext applicationContext;

    public static void initContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(String name, Class<T> cls) {
        return applicationContext.getBean(name, cls);
    }

    public static <T> T getBean(Class<T> cla) {
        String name = cla.getSimpleName();
        if (name != null && name.length() > 1) {
            name = name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        return applicationContext.getBean(name, cla);
    }

}
