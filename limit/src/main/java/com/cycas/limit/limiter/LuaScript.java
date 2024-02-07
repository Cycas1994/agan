package com.cycas.limit.limiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author xin.na
 * @since 2024/2/6 16:55
 */
@Slf4j
public class LuaScript {

    private static final String FIXED_WINDOW_RATE_LIMITER_SCRIPT;
    private static final String SLIDING_WINDOW_RATE_LIMITER_SCRIPT;
    private static final String TOKEN_BUCKET_RATE_LIMITER_SCRIPT;

    private static final RedisScript FIXED_WINDOW_RATE_LIMITER_REDIS_SCRIPT;
    private static final RedisScript SLIDING_WINDOW_RATE_LIMITER_REDIS_SCRIPT;
    private static final RedisScript TOKEN_BUCKET_RATE_LIMITER_REDIS_SCRIPT;


    static {
        FIXED_WINDOW_RATE_LIMITER_SCRIPT = loadRateLimiterScript("scripts/fixed_window_rate_limiter.lua");
        SLIDING_WINDOW_RATE_LIMITER_SCRIPT = loadRateLimiterScript("scripts/sliding_window_rate_limiter.lua");
        TOKEN_BUCKET_RATE_LIMITER_SCRIPT = loadRateLimiterScript("META-INF/scripts/token_bucket_rate_limiter.lua");

        FIXED_WINDOW_RATE_LIMITER_REDIS_SCRIPT = loadRateLimiterRedisScript("scripts/fixed_window_rate_limiter.lua", List.class);
        SLIDING_WINDOW_RATE_LIMITER_REDIS_SCRIPT = loadRateLimiterRedisScript("scripts/sliding_window_rate_limiter.lua", Long.class);
        TOKEN_BUCKET_RATE_LIMITER_REDIS_SCRIPT = loadRateLimiterRedisScript("scripts/token_bucket_rate_limiter.lua", List.class);
    }

    private static String loadRateLimiterScript(String scriptFileName) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(scriptFileName);
        try {
            return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("load rate limiter script failure", e);
            throw new RuntimeException(e);
        }
    }

    private static <T> RedisScript loadRateLimiterRedisScript(String path, Class<T> resultType) {
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(path)));
        redisScript.setResultType(resultType);
        return redisScript;
    }

    public static String getFixedWindowRateLimiterScript() {
        return FIXED_WINDOW_RATE_LIMITER_SCRIPT;
    }

    public static String getSlidingWindowRateLimiterScript() {
        return SLIDING_WINDOW_RATE_LIMITER_SCRIPT;
    }

    public static String getTokenBucketRateLimiterScript() {
        return TOKEN_BUCKET_RATE_LIMITER_SCRIPT;
    }

    public static RedisScript getFixedWindowRateLimiterRedisScript() {
        return FIXED_WINDOW_RATE_LIMITER_REDIS_SCRIPT;
    }

    public static RedisScript getSlidingWindowRateLimiterRedisScript() {
        return SLIDING_WINDOW_RATE_LIMITER_REDIS_SCRIPT;
    }

    public static RedisScript getTokenBucketRateLimiterRedisScript() {
        return TOKEN_BUCKET_RATE_LIMITER_REDIS_SCRIPT;
    }
}
