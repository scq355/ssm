package com.qs.p2p.strategypattern.base_4;

/**
 * Description:策略模式结合简单工厂模式
 * Created by Sun Changqing on 2018/03/18/10:57
 */
public class CashContext {
    private CashSuper cashSuper;

    public CashContext(String type) {
        switch (type) {
            case "正常收费":
                cashSuper = new CashNormal();
                break;
            case "打8折":
                cashSuper = new CashRebate(0.8);
                break;
            case "满300减100":
                cashSuper = new CashReturn(300.0, 100.0);
                break;
            default:
                break;
        }
    }

    public Double getResult(Double money) {
        return cashSuper.acceptCash(money);
    }
}
