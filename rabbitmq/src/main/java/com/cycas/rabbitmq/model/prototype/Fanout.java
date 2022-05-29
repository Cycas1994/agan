package com.cycas.rabbitmq.model.prototype;

import com.cycas.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.Scanner;

/**
 * fanout广播模式
 * 在广播模式下，消息发送流程是这样的：
 * 可以有多个消费者
 * 每个消费者有自己的queue（队列）
 * 每个队列都要绑定到Exchange（交换机）
 * 生产者发送的消息，只能发送到交换机，交换机来决定要发给哪个队列，生产者无法决定。
 * 交换机把消息发送给绑定过的所有队列
 * 队列的消费者都能拿到消息。实现一条消息被多个消费者消费
 */
public class Fanout {

    // 交换机名称
    private static final String EXCHANGE_NAME = "logs";

    /**
     * 消费者2
     */
    static class ReceiveLogs02 {
        public static void main(String[] args) throws IOException {
            consume();
        }
        private static void consume() throws IOException {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            // 声明队列
            String queueName = channel.queueDeclare().getQueue();
            // 绑定交换机队列
            channel.queueBind(queueName, EXCHANGE_NAME, "");
            // 接收消息
            DeliverCallback deliverCallback = ((consumerTag, message) -> {
                System.out.println("ReceiveLogs02控制台打印接收到的消息:" + new String(message.getBody(), "UTF-8"));
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            });
            channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {});
        }
    }

    /**
     * 消费者1
     */
    static class ReceiveLogs01 {
        public static void main(String[] args) throws IOException {
            consume();
        }
        private static void consume() throws IOException {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            // 声明队列
            String queueName = channel.queueDeclare().getQueue();
            // 绑定交换机队列
            channel.queueBind(queueName, EXCHANGE_NAME, "");
            // 接收消息
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                System.out.println("ReceiveLogs01控制台打印接收到的消息:" + new String(message.getBody(), "UTF-8"));
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            };
            channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {});
        }
    }

    static class Publish {
        public static void main(String[] args) throws IOException {
            publish();
        }

        private static void publish() throws IOException {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String message = scanner.next();
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
                System.out.println("生产者发出消息" + message);
            }
        }
    }
}
