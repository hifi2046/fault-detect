package com.hifiax.fault_detect;

import com.hifiax.fault_detect.dao.TradeMapper;
import com.hifiax.fault_detect.data.Trade;
import com.hifiax.fault_detect.mq.Receiver;
import com.hifiax.fault_detect.rule.RuleJudge;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FaultDetectApplicationTests {

    @Autowired
    private TradeMapper tradeMapper;

    @Autowired
    private RuleJudge judge;

    @Test
    void contextLoads() {
    }

    @Test
    void tc001_db_trade_check() {
        Trade trade=tradeMapper.selectById(1);
        System.out.println(trade);
        assertThat(trade.getAccount()).isEqualTo("A123456789");
        assertThat(trade.getAvgAmount()).isEqualTo("100.00");
        assertThat(trade.getLastGpsX()).isEqualTo(1020.0f);
    }

    @Test
    void tc002_mq_receive_check() {
        Receiver receiver=new Receiver();
        receiver.receive();
    }

    @Test
    void tc003_msg2trade() {
        String msg="2|A123456789|W|150.00|100.00|40.00|1000.0|1000.0|1020.0|1020.0|2025-05-09 12:20|2025-05-09 12:10";
        Trade trade=new Trade(msg);
        System.out.println(trade);
    }

    @Test
    void tc004_check_amount_normal() {
        String msg="2|A123456789|W|150.00|100.00|40.00|1000.0|1000.0|1020.0|1020.0|2025-05-09 12:20|2025-05-09 12:10";
        Trade trade=new Trade(msg);
        assertThat(RuleJudge.checkAmount(trade)).isEqualTo("");
    }

    @Test
    void tc005_check_amount_break() {
        String msg="3|A123456789|W|1000.00|100.00|40.00|1000.0|1000.0|1020.0|1020.0|2025-05-09 12:30|2025-05-09 12:20";
        Trade trade=new Trade(msg);
        assertThat(RuleJudge.checkAmount(trade)).isNotEqualTo("");
    }

    @Test
    void tc006_check_location_normal() {
        String msg="2|A123456789|W|150.00|100.00|40.00|1000.0|1000.0|1020.0|1020.0|2025-05-09 12:20|2025-05-09 12:10";
        Trade trade=new Trade(msg);
        assertThat(RuleJudge.checkGps(trade)).isEqualTo("");
    }

    @Test
    void tc007_check_location_break() {
        String msg="4|A123456789|W|100.00|100.00|40.00|2000.0|2000.0|1020.0|1020.0|2025-05-09 12:40|2025-05-09 12:30";
        Trade trade=new Trade(msg);
        assertThat(RuleJudge.checkGps(trade)).isNotEqualTo("");
    }

    @Test
    void tc008_check_all_rules() {
        String msg="4|A123456789|W|100.00|100.00|40.00|2000.0|2000.0|1020.0|1020.0|2025-05-09 12:40|2025-05-09 12:30";
        Trade trade=new Trade(msg);
        judge.checkAllRules(trade);
    }

}
