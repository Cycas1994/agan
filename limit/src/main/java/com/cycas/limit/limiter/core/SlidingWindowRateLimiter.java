package com.cycas.limit.limiter.core;

import com.cycas.limit.limiter.LuaScript;
import com.cycas.limit.model.Result;
import com.cycas.limit.model.Rule;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.List;

/**
 * @author xin.na
 * @since 2024/2/7 13:50
 */
public class SlidingWindowRateLimiter implements RateLimiter {

    private RedisTemplate<String, Object> redisTemplate;
    private RedisScript<Long> script;

    public SlidingWindowRateLimiter(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.script = LuaScript.getSlidingWindowRateLimiterRedisScript();
    }

    @Override
    public Result isAllowed(Rule rule) {
        List<String> keys = this.getKeys(rule.getKey());
        // 新窗口的结束时间
        long now = System.currentTimeMillis();
        // 新窗口的开始时间
        long windowStartTime = now - rule.getRateInterval() * 1000;
        Long result = redisTemplate.execute(this.script, keys, windowStartTime, now, rule.getRate());
        return new Result(result == 1);
    }

    private List<String> getKeys(String key) {
        String prefix = "sliding_window_rate_limiter.{" + key;
        String keys = prefix + "}";
        return Collections.singletonList(keys);
    }
}
