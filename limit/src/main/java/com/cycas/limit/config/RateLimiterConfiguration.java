package com.cycas.limit.config;

import com.cycas.limit.limiter.RateLimiterContext;
import com.cycas.limit.limiter.RuleProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author xin.na
 * @since 2024/2/6 14:17
 */
@Configuration
public class RateLimiterConfiguration {

    @Bean
    public RuleProvider ruleProvider() {
        return new RuleProvider();
    }

    @Bean
    public RateLimiterContext rateLimiterService(RedisTemplate<String, Object> redisTemplate) {
        return new RateLimiterContext(redisTemplate);
    }

}
