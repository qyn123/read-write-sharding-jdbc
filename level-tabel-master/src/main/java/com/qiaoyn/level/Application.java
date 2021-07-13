package com.qiaoyn.level;

import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 水平分表
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-08 13:36
 **/
@SpringBootApplication(exclude = {SpringBootConfiguration.class})
@MapperScan("com.qiaoyn.level.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
