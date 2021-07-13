package com.qiaoyn.database.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-09 14:54
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_order")
public class TOrder {
    private Long orderId;
    private BigDecimal price;
    private Long userId;
    private String status;
}
