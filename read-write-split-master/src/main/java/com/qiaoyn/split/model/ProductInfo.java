package com.qiaoyn.split.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-13 10:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_info")
public class ProductInfo {

    @TableId()
    private Long productInfoId;
    private Long storeInfoId;
    private String productName;
    private String spec;
    private String regionCode;
    private BigDecimal price;
    private String imageUrl;

    @TableField(exist = false)
    private String descript;


}
