package com.qiaoyn.split.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-06-29 23:23
 **/
@Data
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    /**
     * 主键Id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private int age;
}

