package com.soft863.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description:
 * @author: wangchaojie
 * @create: 2019-11-12 10:49
 **/
@SpringBootApplication
@ComponentScan("com.soft863")
@MapperScan("com.soft863.dao")
//@EnableScheduling
public class App {
    public static void main(String[] args) {
        SpringApplication.run (App.class,args);
    }
}
