package com.cycas.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
public class MyController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 只有一个消费者能收到消息
     * @return
     */
    @RequestMapping("/work")
    public String work() {
        for (int i = 0; i < 10; i++) {
            amqpTemplate.convertAndSend("work", "message" + i);
        }
        return "ok";
    }

    /**
     * publish模式：多个消费者同时接收到消息
     * @return
     */
    @RequestMapping("/publish")
    public String publish() {
        for (int i = 0; i < 10; i++) {
            amqpTemplate.convertAndSend("publish_exchange", "", "message" + i);
        }
        return "ok";
    }

    /**
     * 路由模式：具有routing_key的将同时接收到消息
     * @return
     */
    @RequestMapping("/routing1")
    public String routing1() {
        amqpTemplate.convertAndSend("routing_exchange", "routing_key1", "消息routing_key1");
        return "ok";
    }

    @RequestMapping("/routing2")
    public String routing2() {
        amqpTemplate.convertAndSend("routing_exchange", "routing_key2", "消息routing_key2");
        return "ok";
    }

    /**
     * topic模式：根据routing_key匹配的队列将同时接收到消息(通配符匹配)
     * @return
     */
    @RequestMapping("/topic1")
    public String topic1() {
        amqpTemplate.convertAndSend("topics_exchange", "key1", "消息key1");
        return "ok";
    }

    @RequestMapping("/topic2")
    public String topic2() {
        amqpTemplate.convertAndSend("topics_exchange", "topic", "消息topic");
        return "ok";
    }



}
