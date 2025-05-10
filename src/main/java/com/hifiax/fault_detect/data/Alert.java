package com.hifiax.fault_detect.data;

@TableName("alert")
@Data
public class Alert implements Serializable {

    @TableId(value="id", type=IdType.AUTO)
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

}
