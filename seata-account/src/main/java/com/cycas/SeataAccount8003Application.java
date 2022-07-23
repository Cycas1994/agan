package com.cycas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.cycas.dao")
public class SeataAccount8003Application {

    public static void main(String[] args) {
        SpringApplication.run(SeataAccount8003Application.class, args);
    }

}
