package com.cycas.limit.limiter;

import com.cycas.limit.limiter.core.FixedWindowRateLimiter;
import com.cycas.limit.limiter.core.RateLimiter;
import com.cycas.limit.limiter.core.SlidingWindowRateLimiter;
import com.cycas.limit.limiter.core.TokenBucketRateLimiter;
import com.cycas.limit.model.Result;
import com.cycas.limit.model.Rule;
import com.cycas.limit.model.enums.Mode;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xin.na
 * @since 2024/2/6 13:57
 */
public class RateLimiterContext {

    private static final Map<Mode, RateLimiter> RATE_LIMITER_CONTEXT = new HashMap<>();

    public RateLimiterContext(RedisTemplate<String, Object> redisTemplate) {
        RATE_LIMITER_CONTEXT.put(Mode.FIXED_WINDOW, new FixedWindowRateLimiter(redisTemplate));
        RATE_LIMITER_CONTEXT.put(Mode.SLIDING_WINDOW, new SlidingWindowRateLimiter(redisTemplate));
        RATE_LIMITER_CONTEXT.put(Mode.TOKEN_BUCKET, new TokenBucketRateLimiter(redisTemplate));
    }

    public Result isAllowed(Rule rule) {
        RateLimiter rateLimiter = RATE_LIMITER_CONTEXT.get(rule.getMode());
        return rateLimiter.isAllowed(rule);
    }
}
