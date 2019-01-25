package com.qs.p2p.java;

import org.junit.Test;

public class ExceptionTest {
    /**
     *
     */
    @Test
    public void test() {
        System.out.println(test11());

        System.out.println("--------------------");

        System.out.println(test2());
    }

    private String test11() {
        try {
            System.out.println("try block");

            return test12();
        } finally {
            System.out.println("finally block");
        }
    }

    private static String test12() {
        System.out.println("return statement");

        return "after return";
    }


    private int test2() {
        int b = 20;

        try {
            System.out.println("try block");

            return b += 80;
        } catch (Exception e) {

            System.out.println("catch block");
        } finally {

            System.out.println("finally block");

            if (b > 25) {
                System.out.println("b>25, b = " + b);
            }

            return 200;
        }
    }
}
