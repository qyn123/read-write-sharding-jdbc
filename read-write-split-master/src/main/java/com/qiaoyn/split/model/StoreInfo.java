package com.qiaoyn.split.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-13 10:20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("store_info")
public class StoreInfo {

    private Long id;
    private String storeName;
    private Integer reputation;
    private String regionCode;

}
