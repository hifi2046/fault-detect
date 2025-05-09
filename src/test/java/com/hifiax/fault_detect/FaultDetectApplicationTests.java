package com.hifiax.fault_detect;

import com.hifiax.fault_detect.dao.TradeMapper;
import com.hifiax.fault_detect.data.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FaultDetectApplicationTests {

    @Autowired
    private TradeMapper tradeMapper;
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

}
