package com.qiaoyn.split.model.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-13 14:21
 **/
@Data
public class ProductInfoDto {
    private Long productInfoId;
    private String productName;
    private String spec;
    private BigDecimal price;
    private String imageUrl;
    private String regionName;
    private String storeName;
}
