package com.qiaoyn.split;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 读写分离、分库分表综合案例
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-12 15:06
 **/
@SpringBootApplication
@MapperScan("com.qiaoyn.split.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
