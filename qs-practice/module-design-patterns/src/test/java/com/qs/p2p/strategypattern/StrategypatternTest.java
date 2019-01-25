package com.qs.p2p.strategypattern;

import com.qs.p2p.strategypattern.base_2.CashFactory;
import com.qs.p2p.strategypattern.base_2.CashSuper;
import com.qs.p2p.strategypattern.base_3.CashContext;
import com.qs.p2p.strategypattern.base_3.CashNormal;
import com.qs.p2p.strategypattern.base_3.CashRebate;
import com.qs.p2p.strategypattern.base_3.CashReturn;
import org.junit.Test;

import java.util.Scanner;

/**
 * Description:策略模式测试
 * Created by Sun Changqing on 2018/03/18/10:52
 */
public class StrategypatternTest {
    /**
     * 简单工厂模式
     */
    @Test
    public void testSimpleFactory() {
        Double total = 0.0;
        CashSuper cashSuper = CashFactory.returnCashAccept("满300返100");
        Double totalPrices = cashSuper.acceptCash(1000.0);
        total += totalPrices;
        System.out.println("合计：" + total);
    }

    /**
     * 策略模式
     */
    private static void strategypatternTest() {
        Double total = 0.0;
        CashContext cashContext = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入结算类型：打8折， 满300减100， 正常收费");
        String type =  scanner.next();
        switch (type) {
            case "正常收费":
                cashContext = new CashContext(new CashNormal());
                break;
            case "打8折":
                cashContext = new CashContext(new CashRebate(0.8));
                break;
            case "满300减100":
                cashContext = new CashContext(new CashReturn(300.0, 100.0));
                break;
            default:
                break;
        }
        Double totalprices = cashContext.getResult(1000.0);
        total += totalprices;
        System.out.println("合计：" + total);
    }

    private static void strategyAndSimpleFactoryTest() {
        Double total = 0.0;
        com.qs.p2p.strategypattern.base_4.CashContext cashContext = new com.qs.p2p.strategypattern.base_4.CashContext("打8折");
        Double totalprices = cashContext.getResult(1000.0);
        total += totalprices;
        System.out.println(total);
    }

    public static void main(String[] args) {
        System.out.println("====================策略模式====================");
        strategypatternTest();
        System.out.println("====================策略结合简单工厂====================");
        strategyAndSimpleFactoryTest();
    }

}
