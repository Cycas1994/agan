package com.cycas.rabbitmq.model.boot;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class DelayedQueueConsumer {

    @RabbitListener(queues = {"delayed.queue"})
    public void receive(Message message, Channel channel) {
        String msg = new String(message.getBody());
        log.info("当前时间：{}，收到延时队列的消息：{}",new Date(),msg);
    }
}
