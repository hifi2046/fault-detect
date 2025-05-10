package com.hifiax.fault_detect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.hifiax.fault_detect.dao")
@SpringBootApplication
@EnableScheduling
public class FaultDetectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaultDetectApplication.class, args);
    }

}
