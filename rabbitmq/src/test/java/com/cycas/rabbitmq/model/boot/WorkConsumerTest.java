package com.cycas.rabbitmq.model.boot;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorkConsumerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void publish() {
        for (int i = 0; i < 10; i++) {
            String message = "work模型" + i;
            rabbitTemplate.convertAndSend("work",message);
        }
    }

}