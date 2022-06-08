package com.cycas.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 发布确认
 * 在配置文件当中需要添加：
 * spring.rabbitmq.publisher-confirm-type=correlated
 * CORRELATED 值是发布消息成功到交换器后会触发回调方法
 *
 */
@Configuration
public class ConfirmConfig {
    // 交换机名称
    public static final String CONFIRM_EXCHANGE_NAME = "confirm_exchange";
    // 队列名称
    public static final String CONFIRM_QUEUE_NAME = "confirm_queue";
    // RoutingKey名称
    public static final String CONFIRM_ROUTING_KEY = "key1";

    // 声明交换机
    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        return new DirectExchange(CONFIRM_EXCHANGE_NAME);
    }

    // 声明队列
    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

    // 绑定
    @Bean
    public Binding queueBindingExchange(@Qualifier("confirmQueue") Queue confirmQueue, @Qualifier("confirmExchange") DirectExchange confirmExchange) {
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(CONFIRM_ROUTING_KEY);
    }

}
