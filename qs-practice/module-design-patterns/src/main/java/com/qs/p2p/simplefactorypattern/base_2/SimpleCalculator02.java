package com.qs.p2p.simplefactorypattern.base_2;

import java.util.Scanner;

/**
 * Description:
 * Created by Sun Changqing on 2018/03/18/8:15
 */
public class SimpleCalculator02 {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入数字A：");
            String numberA = scanner.next();
            System.out.println("请输入运算符号：（+ - * /）");
            String operate = scanner.next();
            System.out.println("请输入数字B：");
            String numberB = scanner.next();
            Double numberResult = 0.0;

            switch (operate) {
                case "+":
                    numberResult = Double.parseDouble(numberA) + Double.parseDouble(numberB);
                    break;
                case "-":
                    numberResult = Double.parseDouble(numberA) - Double.parseDouble(numberB);
                    break;
                case "*":
                    numberResult = Double.parseDouble(numberA) * Double.parseDouble(numberB);
                    break;
                case "/":
                    if (!numberB.equals("0")) {
                        numberResult = Double.parseDouble(numberA) / Double.parseDouble(numberB);
                    } else {
                        numberResult = -1.0;
                    }
                    break;
            }

            System.out.println("结果是：" + numberResult);
        } catch (Exception e) {
            System.out.println("输入有误："+ e.getMessage());
        }
    }
}