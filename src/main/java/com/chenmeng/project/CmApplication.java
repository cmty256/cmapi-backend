package com.chenmeng.project;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chenmeng.project.mapper")
@EnableDubbo
public class CmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmApplication.class, args);
    }

}
