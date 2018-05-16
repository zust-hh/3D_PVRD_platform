package com.xinmiao.back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.xinmiao.back.mapper")
public class XinMiaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(XinMiaoApplication.class, args);
    }
}
