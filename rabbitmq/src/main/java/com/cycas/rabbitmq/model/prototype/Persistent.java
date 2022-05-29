package com.cycas.rabbitmq.model.prototype;

import com.cycas.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.MessageProperties;

import java.util.Scanner;

/**
 * 持久化
 * 队列实现持久化 需要在声明队列的时候把 durable 参数设置为持久化
 * 消息实现持久化需要在消息生产者修改代码，MessageProperties.PERSISTENT_TEXT_PLAIN 添加这个属性
 * 权重分配
 * 根据不同服务器的大小来自定义分配权重来减少服务器之间的压力
 */
public class Persistent {

    private static final String QUEUE_NAME = "persistent_queue";

    /**
     * 权重1消费者
     */
    static class ConsumeQosOne {
        public static void main(String[] args) throws Exception {
            consume();
        }

        private static void consume() throws Exception {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 接收消息回调
            DeliverCallback deliverCallback = ((consumerTag, message) -> {
                System.out.println("接收到的消息" + new String(message.getBody()));
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            });
            // 取消消息回调
            CancelCallback cancelCallback = consumerTag -> {
                System.out.println(consumerTag + "取消消息");
            };
            // 不公平分发
            channel.basicQos(5);
            channel.basicConsume(QUEUE_NAME, false, deliverCallback, cancelCallback);
        }
    }

    /**
     * 权重2消费者
     */
    static class ConsumeQosTwo {
        public static void main(String[] args) throws Exception {
            consume();
        }

        private static void consume() throws Exception {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 接收消息回调
            DeliverCallback deliverCallback = ((consumerTag, message) -> {
                System.out.println("接收到的消息" + new String(message.getBody()));
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            });
            // 取消消息回调
            CancelCallback cancelCallback = consumerTag -> {
                System.out.println(consumerTag + "取消消息");
            };
            // 不公平分发
            channel.basicQos(2);
            channel.basicConsume(QUEUE_NAME, false, deliverCallback, cancelCallback);
        }
    }

    static class Publish {
        public static void main(String[] args) throws Exception {
            publish();
        }

        private static void publish() throws Exception {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            // 控制台输入
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String message = scanner.next();
                /*
                 * 发送消息
                 * 1.发送到哪个交换机
                 * 2.路由的key值是哪个 本次是队列的名称
                 * 3.其他参数信息
                 * 4.发送消息的消息体
                 * */
                channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            }
        }
    }
}
