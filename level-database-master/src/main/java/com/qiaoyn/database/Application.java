package com.qiaoyn.database;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 水平分库
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-09 14:14
 **/
@SpringBootApplication
@MapperScan("com.qiaoyn.database.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
