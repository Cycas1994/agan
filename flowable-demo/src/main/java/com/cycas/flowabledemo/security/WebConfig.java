package com.cycas.flowabledemo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author xin.na
 * @since 2025/6/24 14:33
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private SignatureAuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/api/workflow/**");
    }
}
