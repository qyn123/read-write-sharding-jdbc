package com.qiaoyn.database.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-09 15:52
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_dict")
public class TDict {

    private Long dictId;
    private String type;
    private String code;
    private String value;
}
