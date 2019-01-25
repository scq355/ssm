package com.qs.p2p.simplefactorypattern.base_3;

/**
 * Description:
 * Created by Sun Changqing on 2018/03/18/8:41
 */
public class Operation {
    public static Double getResult(Double numberA, Double numberB, String operate) {
        Double result = 0.0;
        switch (operate) {
            case "+":
                result = numberA + numberB;
                break;
            case "-":
                result = numberA - numberB;
                break;
            case "*":
                result = numberA * numberB;
                break;
            case "/":
                result = numberA / numberB;
                break;
            default:
                break;
        }
        return result;
    }
}
