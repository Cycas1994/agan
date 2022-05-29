package com.cycas.rabbitmq.model.boot;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Topic
 */
@Component
public class TopicConsumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "topics", type = "topic"),
            key = {"user.save", "user.*"}
    ))
    public void consume1(String message) {
        System.out.println("topic模式消费者1收到消息" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "topics", type = "topic"),
            key = {"order.#", "produce.#", "user.*"}
    ))
    public void receive2(String message) {
        System.out.println("topic模式消费者2收到消息" + message);
    }
}
