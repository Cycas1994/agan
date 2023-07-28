package com.cycas.aop;

import com.cycas.aop.service.TestAopBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.cycas.aop.mapper")
public class AopApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AopApplication.class, args);
        TestAopBean testAopBean = context.getBean(TestAopBean.class);
        testAopBean.testAop();
    }

}
