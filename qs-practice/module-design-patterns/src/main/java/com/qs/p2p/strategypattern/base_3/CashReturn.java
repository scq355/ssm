package com.qs.p2p.strategypattern.base_3;

/**
 * Description: 返利收费：eg:满300返100
 * Created by Sun Changqing on 2018/03/18/10:41
 */
public class CashReturn extends CashSuper {

    private Double moneyCondition = 0.0;
    private Double moneyReturn = 0.0;

    public CashReturn(Double moneyCondition, Double moneyReturn) {
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }

    @Override
    public Double acceptCash(Double money) {
        Double result = money;
        if (money >= moneyCondition) {
            result = money - Math.floor(money / moneyCondition) * moneyReturn;
        }
        return result;
    }
}
