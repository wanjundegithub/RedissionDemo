package com.company;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.company.dao")
public class RedissonBootStrap {

    public static void main(String[] args){
        SpringApplication.run(RedissonBootStrap.class,args);
    }
}
