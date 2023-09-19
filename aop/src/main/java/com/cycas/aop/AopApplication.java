package com.cycas.aop;

import com.cycas.aop.service.TestAopBean;
import com.cycas.mystarter.MyStarterService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.cycas.aop.mapper")
public class AopApplication {

//    @Resource
//    private MyStarterService myStarterService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AopApplication.class, args);
        TestAopBean testAopBean = context.getBean(TestAopBean.class);
//        testAopBean.testAop();
        MyStarterService myStarterService = context.getBean(MyStarterService.class);
        System.out.println(myStarterService.getMessage());
    }

}
