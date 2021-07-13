package com.qiaoyn.level.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-08 13:50
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_order")
public class TOrder {

    @TableField(value = "order_id")
    private Long orderId;
    private BigDecimal price;
    private Long userId;
    private String status;
}
