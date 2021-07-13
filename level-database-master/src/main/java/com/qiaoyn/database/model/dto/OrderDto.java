package com.qiaoyn.database.model.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-09 16:14
 **/
@Data
public class OrderDto {

    private Long orderId;
    private BigDecimal price;
    private Long userId;
    private String status;
    private String value;
}
