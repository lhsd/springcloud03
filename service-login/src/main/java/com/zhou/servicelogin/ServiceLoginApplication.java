package com.zhou.servicelogin;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.zhou.servicelogin.mapper")
@ComponentScan("com.zhou.servicelogin.dao")
public class ServiceLoginApplication  {

    public static void main(String[] args) {
        SpringApplication.run(ServiceLoginApplication.class, args);
    }

}
