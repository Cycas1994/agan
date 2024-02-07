package com.cycas.limit.limiter;

import com.cycas.limit.model.Rule;
import com.cycas.limit.model.annotation.RateLimit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.convert.DurationStyle;

/**
 * @author xin.na
 * @since 2024/2/6 14:28
 */
@Slf4j
public class RuleProvider {

    private String getKey(MethodSignature signature) {
        return String.format("%s.%s", signature.getDeclaringTypeName(), signature.getMethod().getName());
    }

    private int getRate(RateLimit rateLimit) {
        return rateLimit.rate();
    }

    private int getBucketCapacity(RateLimit rateLimit) {
        return rateLimit.bucketCapacity();
    }

    private long getRateInterval(RateLimit rateLimit) {
        return DurationStyle.detectAndParse(rateLimit.rateInterval()).getSeconds();
    }

    public Rule getRateLimitRule(JoinPoint joinPoint, RateLimit rateLimit) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String rateLimitKey = this.getKey(signature);
        int rate = this.getRate(rateLimit);
        int bucketCapacity = this.getBucketCapacity(rateLimit);
        long rateInterval = this.getRateInterval(rateLimit);

        Rule rule = new Rule(rateLimitKey, rate, rateLimit.mode());
        rule.setRateInterval(Long.valueOf(rateInterval).intValue());
        rule.setFallbackFunction(rateLimit.fallbackFunction());
        rule.setRequestedTokens(rateLimit.requestTokens());
        rule.setBucketCapacity(bucketCapacity);
        return rule;
    }

}
