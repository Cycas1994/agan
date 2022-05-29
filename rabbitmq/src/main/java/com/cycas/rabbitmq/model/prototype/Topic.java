package com.cycas.rabbitmq.model.prototype;

import com.cycas.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import sun.plugin2.message.Message;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Topic
 * Topic类型的Exchange与Direct相比，都是可以根据RoutingKey把消息路由到不同的队列。
 * 只不过Topic类型Exchange可以让队列在绑定Routing key 的时候使用通配符！
 * 这种模型Routing key 一般都是由一个或多个单词组成，多个单词之间以”.”分割，例如： item.insert
 */
public class Topic {

    private static final String EXCHANGE_NAME = "topic_logs";

    /**
     * 消费者2
     * *.*.rabbit
     * lazy.#
     */
    static class Consume2 {
        public static void main(String[] args) throws IOException {
            consume();
        }
        private static void consume() throws IOException {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            // 声明队列
            channel.queueDeclare("qa2", false, false, false, null);
            // 绑定交换机队列
            channel.queueBind("qa2", EXCHANGE_NAME, "*.*.rabbit");
            channel.queueBind("qa2", EXCHANGE_NAME, "lazy.#");
            // 接收消息
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                System.out.println("q2接收消息:" + new String(message.getBody()));
                System.out.println("q2队列绑定key：" + message.getEnvelope().getRoutingKey());
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            };
            channel.basicConsume("qa2", false, deliverCallback, consumerTag -> {});
        }
    }

    /**
     * 消费者1
     * (*.orange.*)
     */
    static class Consume1 {
        public static void main(String[] args) throws IOException {
            consume();
        }
        private static void consume() throws IOException {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            // 声明队列
            channel.queueDeclare("q1", false, false, false, null);
            // 绑定交换机队列
            channel.queueBind("q1", EXCHANGE_NAME, "*.orange.*");
            // 接收消息
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                System.out.println("q1接收消息" + new String(message.getBody()));
                System.out.println("q1接收队列绑定key：" + message.getEnvelope().getRoutingKey());
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            };
            channel.basicConsume("q1", false, deliverCallback, consumerTag -> {});

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
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            /*
             * Q1-->绑定的是
             *           中间带orange的3个单词的信道(*.orange.*)
             * Q2-->绑定的是
             *           最后一个单词是rabbit的3个单词的信道(*.*.rabbit)
             *           第一个单词是lazy的多个单词的信道(lazy.#)
             * */
            HashMap<String, String> bindingKeymap = new HashMap<>();
            bindingKeymap.put("quick.orange.rabbit","被队列Q1Q2接收到");
            bindingKeymap.put("lazy.orange.elephant","被队列Q1Q2接收到");
            bindingKeymap.put("quick.orange.fox","被队列Q1接收到");
            bindingKeymap.put("lazy.brown.fox","被队列Q2接收到");
            bindingKeymap.put("lazy.pink.rabbit","虽然满足两个绑定，但只被队列Q2接收一次");
            bindingKeymap.put("quick.brown.fox","不匹配任何绑定不会被任何队列接收到，会被丢弃");
            bindingKeymap.put("quick.orange.male.rabbit","是四个单词不匹配任何绑定，会被丢弃");
            bindingKeymap.put("lazy.orange.male.rabbit","被Q2接收到");
            for (Map.Entry<String, String> entry : bindingKeymap.entrySet()) {
                channel.basicPublish(EXCHANGE_NAME, entry.getKey(), null, entry.getValue().getBytes());
                System.out.println("生产者发出消息" + entry.getValue());
            }
        }
    }
}
