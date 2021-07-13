package com.qiaoyn.split.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-12 17:48
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_descript")
public class ProductDescript {

    private Long id;
    private Long productInfoId;
    private String descript;
    private Long storeInfoId;

}
