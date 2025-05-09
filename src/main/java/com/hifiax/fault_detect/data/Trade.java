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
    private BigDecimal avg_amount;

    @TableField("std")
    private BigDecimal std;

    @TableField("gps_x")
    private Float gps_x;

    @TableField("gps_y")
    private Float gps_y;

    @TableField("last_gps_x")
    private Float last_gps_x;

    @TableField("last_gps_y")
    private Float last_gps_y;

    @TableField("last_time")
    private LocalDateTime last_time;

    @TableField("trans_time")
    private LocalDateTime trans_time;

}
