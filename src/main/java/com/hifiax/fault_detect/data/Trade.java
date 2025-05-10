package com.hifiax.fault_detect.data;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@TableName("trade")
@Data
public class Trade implements Serializable {

    @TableId(value="id", type=IdType.AUTO)
    private Integer id;

    @TableField("account")
    private String account;

    @TableField("side")
    private String side;

    @TableField("amount")
    private BigDecimal amount;

    @TableField("avg_amount")
    private BigDecimal avgAmount;

    @TableField("std")
    private BigDecimal std;

    @TableField("gps_x")
    private Float gpsX;

    @TableField("gps_y")
    private Float gpsY;

    @TableField("last_gps_x")
    private Float lastGpsX;

    @TableField("last_gps_y")
    private Float lastGpsY;

    @TableField("last_time")
    private LocalDateTime lastTime;

    @TableField("trans_time")
    private LocalDateTime transTime;

    Trade() {
        
    }

    public Trade(String msg) {
        String[] list=msg.split("\\|");
        System.out.println(msg);
        for(String t : list) {
            System.out.println(t);
        }
        this.id=Integer.parseInt(list[0]);
        this.account=list[1];
        this.side=list[2];
        this.amount=new BigDecimal(list[3]);
        this.avgAmount=new BigDecimal(list[4]);
        this.std=new BigDecimal(list[5]);
        this.gpsX=Float.parseFloat(list[6]);
        this.gpsY=Float.parseFloat(list[7]);
        this.lastGpsX=Float.parseFloat(list[8]);
        this.lastGpsY=Float.parseFloat(list[9]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        System.out.println(list[10]);
        this.lastTime=LocalDateTime.parse(list[10], formatter);;
//        System.out.println(list[11]);
        this.transTime=LocalDateTime.parse(list[11], formatter);;;
    }
}
