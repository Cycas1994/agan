package com.cycas.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author NaXin
 * @since 2023-04-23
 */
@Slf4j
@Service
public class TestAopBean {

    public void testAop() {
        log.info("I am the true aop bean");
    }
}
