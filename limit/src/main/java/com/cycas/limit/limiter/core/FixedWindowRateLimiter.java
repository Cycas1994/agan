package com.cycas.limit.limiter.core;

import com.cycas.limit.limiter.LuaScript;
import com.cycas.limit.model.Result;
import com.cycas.limit.model.Rule;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Collections;
import java.util.List;

/**
 * @author xin.na
 * @since 2024/2/6 13:59
 */
public class FixedWindowRateLimiter implements RateLimiter {

    private RedisTemplate<String, Object> redisTemplate;
    private RedisScript<List<Long>> script;

    public FixedWindowRateLimiter(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.script = LuaScript.getFixedWindowRateLimiterRedisScript();
    }

    @Override
    public Result isAllowed(Rule rule) {
        List<String> keys = this.getKeys(rule.getKey());
        List<Long> results = redisTemplate.execute(script, keys, rule.getRate(), rule.getRateInterval());
        boolean isAllowed = results.get(0) == 1L;
        long ttl = results.get(1);
        return new Result(isAllowed, ttl);
    }

    private List<String> getKeys(String key) {
        String prefix = "fixed_window_rate_limiter.{" + key;
        String keys = prefix + "}";
        return Collections.singletonList(keys);
    }
}
