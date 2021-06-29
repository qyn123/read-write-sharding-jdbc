package com.qiaoyn.read;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-06-28 22:46
 **/
@SpringBootApplication
@MapperScan("com.qiaoyn.read.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
