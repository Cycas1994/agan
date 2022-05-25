package com.cycas.redis.controller;

import com.cycas.redis.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
public class IndexController implements InitializingBean, DisposableBean {

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IndexService indexService;

    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct come in");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet come in");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy come in");
    }
}
