package com.cycas.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void rqmMsgTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是一个消息");
        map.put("data", Arrays.asList("hello", 123, true));

        rabbitTemplate.convertAndSend("fanout_exchange", "atguigu.news", map);
    }
}
