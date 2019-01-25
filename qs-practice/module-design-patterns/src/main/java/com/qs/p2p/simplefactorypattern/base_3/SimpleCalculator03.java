package com.qs.p2p.simplefactorypattern.base_3;

import java.util.Scanner;

/**
 * Description:
 * Created by Sun Changqing on 2018/03/18/8:45
 */
public class SimpleCalculator03 {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入数字A：");
            String numberA = scanner.next();
            System.out.println("请输入运算符号：（+ - * /）");
            String operate = scanner.next();
            System.out.println("请输入数字B：");
            String numberB = scanner.next();
            Double numberResult = Operation.getResult(Double.parseDouble(numberA), Double.parseDouble(numberB), operate);
            System.out.println("结果是：" + numberResult);
        } catch (Exception e) {
            System.out.println("输入数据有误：" + e.getMessage());
        }
    }
}
