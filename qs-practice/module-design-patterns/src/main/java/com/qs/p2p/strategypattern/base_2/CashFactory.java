package com.qs.p2p.strategypattern.base_2;

/**
 * Description: 现金收费工厂
 * Created by Sun Changqing on 2018/03/18/10:45
 */
public class CashFactory {
    public static CashSuper returnCashAccept(String type) {
        CashSuper cashSuper = null;
        switch (type) {
            case "正常收费":
                cashSuper = new CashNormal();
                break;
            case "满300返100":
                cashSuper = new CashReturn(300.0, 100.0);
                break;
            case "打8折":
                cashSuper = new CashRebate(0.8);
                break;
            default:
                break;
        }
        return cashSuper;
    }
}
