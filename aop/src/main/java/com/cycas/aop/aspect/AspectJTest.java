package com.cycas.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author NaXin
 * @since 2023-04-23
 */
@Slf4j
@Aspect
@Component
public class AspectJTest {

    @Pointcut("execution(* com.cycas.aop.service.TestAopBean.*(..))")
    public void test() {

    }

    @Before("test()")
    public void beforeTest() {
        log.info("before test");
    }
}
