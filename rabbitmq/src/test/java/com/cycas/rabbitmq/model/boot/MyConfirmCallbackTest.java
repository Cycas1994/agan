package com.cycas.rabbitmq.model.boot;

import com.cycas.rabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class MyConfirmCallbackTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void receiveConfirmMessage() {

        CorrelationData correlationData1 = new CorrelationData("1");
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME, ConfirmConfig.CONFIRM_ROUTING_KEY, "message key1", correlationData1);
        log.info("发送消息内容为：{}", "message key1");

        CorrelationData correlationData2 = new CorrelationData("2");
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME, ConfirmConfig.CONFIRM_ROUTING_KEY + "2", "message key12", correlationData2);
        log.info("发送消息内容为：{}", "message key12");
    }
}