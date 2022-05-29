package com.cycas.rabbitmq.model.boot;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * work工作模型
 */
@Component
public class WorkConsumer {

    /**
     * 第一个消费者
     * @param message
     */
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void consume1(String message) {
        System.out.println("工作模式消费者1收到消息" + message);
    }

    /**
     * 第二个消费者
     * @param message
     */
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void consume2(String message) {
        System.out.println("工作模式消费者2收到消息" + message);

    }
}
