package com.cycas.limit.config;

import io.netty.channel.nio.NioEventLoopGroup;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author xin.na
 * @since 2024/2/6 14:22
 */
@Configuration
public class RedissonConfiguration {

    @Resource
    private RedisProperties redisProperties;
    private static final String PREFIX = "redis://";

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        String url = PREFIX + redisProperties.getHost() + ":" + redisProperties.getPort();
        config.useSingleServer()
                .setAddress(url)
                .setDatabase(redisProperties.getDatabase())
                .setPassword(redisProperties.getPassword());
        config.setEventLoopGroup(new NioEventLoopGroup());
        return Redisson.create(config);
    }

}
