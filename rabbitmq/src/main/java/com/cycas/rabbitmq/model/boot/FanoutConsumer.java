package com.cycas.rabbitmq.model.boot;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * fanout广播模式
 */
@Component
public class FanoutConsumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("fanout_queue"),
            exchange = @Exchange(value = "logs", type = "fanout")))
    public void consume1(String message) {
        System.out.println("广播模式消费者1接收消息：" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("fanout_queue"),
            exchange = @Exchange(value = "logs", type = "fanout")
    ))
    public void consume2(String message) {
        System.out.println("广播模式消费者2接收消息：" + message);
    }
}
