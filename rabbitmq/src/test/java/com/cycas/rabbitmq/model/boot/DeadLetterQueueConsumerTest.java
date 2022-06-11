package com.cycas.rabbitmq.model.boot;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeadLetterQueueConsumerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void receive() {
        int[] arr = {20000, 2000};
        for (int i = 0; i < 2; i++) {
            String message = "message" + i;
            int finalI = i;
            rabbitTemplate.convertAndSend("delayed.exchange", "delayed.routingkey", message, msg -> {
                msg.getMessageProperties().setDelay(arr[finalI]);
                return msg;
            });
        }
    }
}