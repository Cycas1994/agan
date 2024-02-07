package com.cycas.limit.limiter.core;

import com.cycas.limit.limiter.LuaScript;
import com.cycas.limit.model.Result;
import com.cycas.limit.model.Rule;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

/**
 * @author xin.na
 * @since 2024/2/7 15:39
 */
public class TokenBucketRateLimiter implements RateLimiter {

    private RedisTemplate<String, Object> redisTemplate;
    private RedisScript<List<Long>> script;

    public TokenBucketRateLimiter(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.script = LuaScript.getTokenBucketRateLimiterRedisScript();
    }

    @Override
    public Result isAllowed(Rule rule) {
        List<String> keys = getKeys(rule.getKey());
        List<Long> results = redisTemplate.execute(this.script, keys,
                rule.getRate(), rule.getBucketCapacity(), Instant.now().getEpochSecond(), rule.getRequestedTokens());
        boolean isAllowed = results.get(0) == 1L;
        long newTokens = results.get(1);
        return new Result(isAllowed, newTokens);
    }

    static List<String> getKeys(String key) {
        String prefix = "token_bucket_rate_limiter.{" + key;
        String tokenKey = prefix +"}.tokens";
        String timestampKey = prefix + "}.timestamp";
        return Arrays.asList(tokenKey, timestampKey);
    }
}
