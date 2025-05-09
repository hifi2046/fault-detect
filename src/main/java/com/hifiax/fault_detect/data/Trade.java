package com.hifiax.fault_detect.data;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

}
