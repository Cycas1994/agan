package com.cycas.mystarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author NaXin
 * @since 2023-08-01
 */
@Configuration
@EnableConfigurationProperties(MyStarterProperties.class)
@ConditionalOnProperty(prefix = "mystarter", name = "enabled", havingValue = "true", matchIfMissing = true)
public class MyStarterAutoConfiguration {

    private final MyStarterProperties properties;

    public MyStarterAutoConfiguration(MyStarterProperties properties) {
        this.properties = properties;
    }

    @Bean
    public MyStarterService myStarterService() {
        return new MyStarterService(properties.getMessage());
    }
}
