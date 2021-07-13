package com.qiaoyn.split.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-12 17:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("region")
public class Region {
    private Long id;
    private String regionCode;
    private String regionName;
    private Integer level;
    private String parentRegionCode;

}
