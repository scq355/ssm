package com.qs.p2p.java.annotation.runtime;

import java.lang.annotation.*;

/**
 * https://blog.csdn.net/u011130752/article/details/51773750
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface MyAnnotation {
    String name();
    String value() default "";
    String[] newNames();
}
