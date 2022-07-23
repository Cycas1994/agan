package com.cycas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.cycas.dao")
public class SeataStorage8002Application {

    public static void main(String[] args) {
        SpringApplication.run(SeataStorage8002Application.class, args);
    }

}
