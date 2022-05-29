package com.cycas.rabbitmq.model.prototype;

import com.cycas.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.Scanner;

/**
 * Direct(直连)模式
 * 队列与交换机的绑定，不能是任意绑定了，而是要指定一个RoutingKey（路由key）
 * 消息的发送方在 向 Exchange发送消息时，也必须指定消息的 RoutingKey。
 * Exchange不再把消息交给每一个绑定的队列，而是根据消息的Routing Key进行判断，只有队列的Routingkey与消息的 Routing key完全一致，才会接收到消息
 */
public class Direct {

    private static final String EXCHANGE_NAME = "direct_logs";

    /**
     * 消费者2
     */
    static class ConsumerLog2 {
        public static void main(String[] args) throws IOException {
            consume();
        }
        private static void consume() throws IOException {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            // 声明队列
            channel.queueDeclare("disk", false, false, false, null);
            // 绑定交换机队列
            channel.queueBind("disk", EXCHANGE_NAME, "error");
            // 接收消息
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                System.out.println("ConsumerLog2控制台打印接收到的消息:"+ new String(message.getBody(),"UTF-8"));
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            };
            channel.basicConsume("disk", false, deliverCallback, consumerTag -> {});
        }
    }

    /**
     * 消费者1
     */
    static class ConsumerLog1 {
        public static void main(String[] args) throws IOException {
            consume();
        }
        private static void consume() throws IOException {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            // 声明队列
            channel.queueDeclare("console", false, false, false, null);
            // 绑定交换机队列
            channel.queueBind("console", EXCHANGE_NAME, "info");
            channel.queueBind("console", EXCHANGE_NAME, "warning");
            // 接收消息
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                System.out.println("ConsumerLog1控制台打印接收到的消息:"+ new String(message.getBody(),"UTF-8"));
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            };
            channel.basicConsume("console", false, deliverCallback, consumerTag -> {});
        }
    }

    /**
     * 生产者
     */
    static class Publish {
        public static void main(String[] args) throws IOException {
            publish();
        }
        private static void publish() throws IOException {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            // 输入
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String message = scanner.next();
                channel.basicPublish(EXCHANGE_NAME, "error", null, message.getBytes());
                System.out.println("生产者发出消息" + message);
            }
        }
    }
}
