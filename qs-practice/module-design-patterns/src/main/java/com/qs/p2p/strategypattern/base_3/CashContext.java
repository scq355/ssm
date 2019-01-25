package com.qs.p2p.strategypattern.base_3;

/**
 * Description:
 * Created by Sun Changqing on 2018/03/18/10:57
 */
public class CashContext {
    private CashSuper cashSuper;

    public CashContext(CashSuper cashSuper) {
        this.cashSuper = cashSuper;
    }

    public Double getResult(Double money) {
        return cashSuper.acceptCash(money);
    }
}
