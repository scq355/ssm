package com.qs.p2p.simplefactorypattern.base_1;

import java.util.Scanner;

/**
 * Description: 简单计算器
 * Created by Sun Changqing on 2018/03/18/7:58
 */
public class SimpleCalculator01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入数字A：");
        String A = scanner.next();
        System.out.println("请输入运算符号：（+ - * /）");
        String O = scanner.next();
        System.out.println("请输入数字B：");
        String B = scanner.next();
        Double D = 0.0;
        if (O.equals("+")) {
            D = Double.parseDouble(A) + Double.parseDouble(B);
        }
        if (O.equals("-")) {
            D = Double.parseDouble(A) - Double.parseDouble(B);
        }
        if (O.equals("*")) {
            D = Double.parseDouble(A) * Double.parseDouble(B);
        }
        if (O.equals("/")) {
            D = Double.parseDouble(A) / Double.parseDouble(B);
        }
        System.out.println("结果是：" + D);
    }
}
