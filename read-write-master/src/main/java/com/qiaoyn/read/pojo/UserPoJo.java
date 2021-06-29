package com.qiaoyn.read.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-06-28 23:01
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class UserPoJo {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String city;
}
