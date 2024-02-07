package com.cycas.limit.aspect;

import com.cycas.limit.limiter.RateLimiterContext;
import com.cycas.limit.limiter.RuleProvider;
import com.cycas.limit.exception.RateLimitException;
import com.cycas.limit.model.Result;
import com.cycas.limit.model.Rule;
import com.cycas.limit.model.annotation.RateLimit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author xin.na
 * @since 2024/2/6 14:28
 */
@Slf4j
@Aspect
@Component
public class RateLimitAspect {

    private final RateLimiterContext rateLimiterContext;
    private final RuleProvider ruleProvider;

    public RateLimitAspect(RateLimiterContext rateLimiterContext, RuleProvider ruleProvider) {
        this.rateLimiterContext = rateLimiterContext;
        this.ruleProvider = ruleProvider;
    }

    @Pointcut("@annotation(com.cycas.limit.model.annotation.RateLimit)")
    public void executeService() {}

    @Around("executeService()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = this.getMethod(joinPoint);
        RateLimit rateLimit = method.getAnnotation(RateLimit.class);
        Rule rule = ruleProvider.getRateLimitRule(joinPoint, rateLimit);
        Result result = rateLimiterContext.isAllowed(rule);
        if (!result.isAllow()) {
            log.info("Trigger current limiting,key:{}", rule.getKey());
            throw new RateLimitException("Too Many Requests", result.getExtra(), rule.getMode());
        }
        return joinPoint.proceed();
    }

    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(),
                        method.getParameterTypes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return method;
    }

}
