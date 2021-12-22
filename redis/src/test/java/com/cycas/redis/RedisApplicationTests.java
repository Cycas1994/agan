package com.cycas.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class RedisApplicationTests {

    @Before
    public void init() {
        System.out.println(this.getClass().getName() + " start test.");
    }

    @After
    public void after() {
        System.out.println(this.getClass().getName() + " finished test.");
    }

    @Test
    void contextLoads() {
    }

}
