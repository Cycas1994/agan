package com.cycas.rabbitmq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {

    /**
     * 获取一个连接的 channel
     *
     * @return
     * @throws Exception
     */
    public static Channel getChannel() {
        try {
            // 创建一个连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            // 工厂IP，连接RabbitMQ队列
            factory.setHost("localhost");
            // 连接端口号
            factory.setPort(5672);
            // 用户名
            factory.setUsername("guest");
            // 密码
            factory.setPassword("guest");
            // 创建连接
            Connection connection = factory.newConnection();
            // 获取通道
            Channel channel = connection.createChannel();
            return channel;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}
