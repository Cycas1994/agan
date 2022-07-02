package com.cycas.rabbitmq.model.boot;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Direct(直连)模式
 */
@Component
public class DirectConsumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("direct_queue"),
            exchange = @Exchange(value = "directs", type = "direct"),
            key = {"info", "error", "warn"}
    ))
    public void consume1(String message) {
        System.out.println("直连模式消费者1收到消息" + message);

    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("direct_queue"),
            exchange = @Exchange(value = "directs", type = "direct"),
            key = {"error"}
    ))
    public void consume2(String message) {
        System.out.println("直连模式消费者2收到消息" + message);
    }
}
