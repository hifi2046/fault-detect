package com.hifiax.fault_detect.rule;

import com.hifiax.fault_detect.data.Trade;

public class RuleJudge {

    public static String checkAmount(Trade trade) {
        if( trade.getAmount().doubleValue() > trade.getAvgAmount().doubleValue()+trade.getStd().doubleValue()*3) {
            return "Alert: amount rule broken!";
        }
        return "";
    }

    public static String checkGps(Trade trade) {
        Float deltaX = trade.getGpsX() - trade.getLastGpsX();
        Float deltaY = trade.getGpsY() - trade.getLastGpsY();
        if( deltaX * deltaX + deltaY * deltaY > 1000000.0 ) {
            return "Alert: location rule broken!";
        }
        return "";
    }
}
