package com.hifiax.fault_detect.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskScheduler {

    @Autowired
    private RuleJudge judge;

    @Scheduled(fixedRate=2000)
    public void scheduleTask() {
        System.out.println("*");
        judge.loopCheck();
    }
}
