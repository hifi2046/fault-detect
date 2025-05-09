package com.hifiax.fault_detect;

import com.hifiax.fault_detect.dao.TradeMapper;
import com.hifiax.fault_detect.data.Trade;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FaultDetectApplicationTests {

    @Autowired
    private TradeMapper tradeMapper;
    @Test
    void contextLoads() {
        Trade trade=tradeMapper.selectById(1);
        System.out.println(trade);
    }

}
