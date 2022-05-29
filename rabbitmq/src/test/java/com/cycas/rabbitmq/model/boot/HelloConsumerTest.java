package com.cycas.rabbitmq.model.boot;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloConsumerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void publish() {
        rabbitTemplate.convertAndSend("hello", "hello world!");
    }
}