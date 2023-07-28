package com.cycas.aop.service;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
public class TestAopBeanTest {

    @Resource
    private TestAopBean testAopBean;

    @Test
    public void testAop() {
        testAopBean.testAop();
    }
}