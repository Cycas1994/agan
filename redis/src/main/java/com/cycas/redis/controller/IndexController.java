package com.cycas.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
public class IndexController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private Lock lock = new ReentrantLock();

    @RequestMapping("/buy")
    public String index() {
        lock.lock();
        try {
            String result = redisTemplate.opsForValue().get("goods:001");
            // 剩余商品数
            int total = result == null ? 0 : Integer.parseInt(result);
            if (total > 0) {
                int realTotal = total - 1;
                redisTemplate.opsForValue().set("goods:001", String.valueOf(realTotal));
                System.out.println("购买商品成功，库存还剩："+realTotal +"件， 服务端口为8080");
                return "购买商品成功，库存还剩："+realTotal +"件， 服务端口为8001";
            }
            System.out.println("购买商品失败，服务端口为8080");
            return "购买商品失败，服务端口为8001";
        } finally {
            lock.unlock();
        }
    }
}
