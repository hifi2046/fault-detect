package com.hifiax.fault_detect.data;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@TableName("alert")
@Data
public class Alert implements Serializable {

    @TableId(value="id", type=IdType.AUTO)
//    @OrderBy(value = "id desc")
    private Integer id;

    @TableField("account")
    private String account;

    @TableField("side")
    private String side;

    @TableField("amount")
    private BigDecimal amount;

    @TableField("rule")
    private Integer rule;

    @TableField("content")
    private String content;

    Alert() {

    }

    public Alert(Trade trade, Integer rule, String content) {
        this.account=trade.getAccount();
        this.side=trade.getSide();
        this.amount=trade.getAmount();
        this.rule=rule;
        this.content=content;
    }

}
