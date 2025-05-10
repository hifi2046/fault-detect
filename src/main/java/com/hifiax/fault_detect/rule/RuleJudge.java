package com.hifiax.fault_detect.rule;

import com.hifiax.fault_detect.data.Trade;

public class RuleJudge {

    public static boolean checkAmount(Trade trade) {
        if( trade.getAmount().doubleValue() > trade.getAvgAmount().doubleValue()+trade.getStd().doubleValue()*3) {
            System.out.println("Alert: amount rule broken!");
            return true;
        }
        return false;
    }

    public static boolean checkGps(Trade trade) {
        Float deltaX = trade.getGpsX() - trade.getLastGpsX();
        Float deltaY = trade.getGpsY() - trade.getLastGpsY();
        if( deltaX * deltaX + deltaY * deltaY > 1000000.0 ) {
            System.out.println("Alert: location rule broken!");
            return true;
        }
        return false;
    }
}
