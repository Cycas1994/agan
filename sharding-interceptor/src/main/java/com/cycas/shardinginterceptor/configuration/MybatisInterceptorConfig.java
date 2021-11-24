package com.cycas.shardinginterceptor.configuration;

import com.cycas.shardinginterceptor.interceptor.MybatisInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MybatisInterceptorConfig {

    @Bean
    public String interceptor(SqlSessionFactory sqlSessionFactory) {
        MybatisInterceptor mybatisInterceptor = new MybatisInterceptor();
        Properties properties = new Properties();
        properties.setProperty("hcUpdateEventEnable", "true");
        properties.setProperty("applicationName", "applicationName");
        properties.setProperty("hcUpdateTables", "t_user");
        mybatisInterceptor.setProperties(properties);
        sqlSessionFactory.getConfiguration().addInterceptor(mybatisInterceptor);
        return "interceptor";
    }
}
