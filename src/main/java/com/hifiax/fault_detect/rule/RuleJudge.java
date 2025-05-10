package com.hifiax.fault_detect.rule;

import com.hifiax.fault_detect.dao.AlertMapper;
import com.hifiax.fault_detect.data.Trade;
import com.hifiax.fault_detect.data.Alert;
import com.hifiax.fault_detect.mq.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class RuleJudge {

    @Autowired
    AlertMapper alertMapper;

    @Autowired
    Receiver receiver;

    public RuleJudge() {

    }

    public void loopCheck() {
        String msg=receiver.receive();
        if(msg != "") {
            Trade trade=new Trade(msg);
            checkAllRules(trade);
        }
    }

    public void checkAllRules(Trade trade) {
        String res;
        res=checkAmount(trade);
        if(res!="") {
            Alert alert=new Alert(trade, 1, res);
            System.out.println(alert);
            alertMapper.insert(alert);
        }
        res=checkGps(trade);
        if(res!="") {
            Alert alert=new Alert(trade, 2, res);
            System.out.println(alert);
            alertMapper.insert(alert);
        }
    }

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
