package com.qiaoyn.split;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-06-29 22:51
 **/
@SpringBootApplication
@MapperScan("com.qiaoyn.split.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
