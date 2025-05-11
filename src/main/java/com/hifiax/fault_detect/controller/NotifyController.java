package com.hifiax.fault_detect.controller;

import com.hifiax.fault_detect.data.Trade;
import com.hifiax.fault_detect.mq.Receiver;
import com.hifiax.fault_detect.rule.RuleJudge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1")
public class NotifyController {

    @Autowired
    Receiver receiver;

    @Autowired
    RuleJudge judge;

    @PutMapping("/notify")
    public String notifyMessage() {
        String msg=receiver.receive();
        if(msg != "") {
            Trade trade=new Trade(msg);
            judge.checkAllRules(trade);
        }
        return "ok";
    }
}
