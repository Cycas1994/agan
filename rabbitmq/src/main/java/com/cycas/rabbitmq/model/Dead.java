package com.cycas.rabbitmq.model;

import com.cycas.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 死信队列
 * producer 将消息投递到 broker 或者直接到 queue 里了，consumer 从 queue 取出消息进行消费，
 * 但某些时候由于特定的原因导致 queue 中的某些消息无法被消费，这样的消息如果没有后续的处理，就变成了死信，有死信自然就有了死信队列。
 * 死信来源:
 * 1、消息 TTL 过期
 * 2、队列达到最大长度(队列满了，无法再添加数据到 mq 中)
 * 3、消息被拒绝(basic.reject 或 basic.nack)并且 requeue=false.
 */
public class Dead {

    // 普通交换机
    private static final String NORMAL_EXCHANGE = "normal_exchange";
    // 死信交换机
    private static final String DEAD_EXCHANGE = "dead_exchange";

    /**
     * 消息过期/队列满了 死信队列 消费者（公共的）
     */
    static class DeadQueueConsume {
        public static void main(String[] args) throws IOException {
            consume();
        }
        private static void consume() throws IOException {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明交换机
            channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
            // 声明死信队列
            String deadQueue = "dead_queue";
            channel.queueDeclare(deadQueue, false, false, false, null);
            // 绑定交换机队列
            channel.queueBind(deadQueue, DEAD_EXCHANGE, "lisi");
            // 接收消息
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                System.out.println("死信队列消费者 接收到消息" + new String(message.getBody()));
            };
            channel.basicConsume(deadQueue, true, deliverCallback, consumerTag -> {});
        }
    }

    /**
     * 消息被拒 正常队列 消费者
     */
    static class RejectConsume {
        public static void main(String[] args) throws IOException {
            consume();
        }
        private static void consume() throws IOException {
            // 获取信道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明交换机
            channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
            channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);

            // 声明死信队列
            String deadQueue = "dead_queue";
            channel.queueDeclare(deadQueue, false, false, false, null);
            // 绑定交换机队列
            channel.queueBind(deadQueue, DEAD_EXCHANGE, "lisi");

            // 正常队列绑定死信队列
            Map<String, Object> props = new HashMap<>();
            // 设置死信队列交换机
            props.put("x-dead-letter-exchange", DEAD_EXCHANGE);
            // 设置死信队列routing key
            props.put("x-dead-letter-routing-key", "lisi");
            // 声明正常队列
            String normalQueue = "normal_queue";
            channel.queueDeclare(normalQueue, false, false, false, props);
            // 绑定交换机队列
            channel.queueBind(normalQueue, NORMAL_EXCHANGE, "zhangsan");

            // 接收消息
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                String content = new String(message.getBody());
                if (content.equals("info5")) {
                    System.out.println("正常队列拒绝消费消息：" + content);
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                } else if (content.equals("info6")) {
                    System.out.println("正常队列否定消费消息：" + content);
                    channel.basicNack(message.getEnvelope().getDeliveryTag(), false, false);
                } else {
                    System.out.println("正常队列接收消息:" + content);
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                }
            };
            channel.basicConsume(normalQueue, false, deliverCallback, consumerTag -> {});
        }
    }

    /**
     * 消息被拒 死信队列 生产者
     */
    static class RejectPublish {
        public static void main(String[] args) throws IOException {
            publish();
        }
        private static void publish() throws IOException {
            // 获取信道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明交换机
//            channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
            for (int i = 0; i < 10; i++) {
                String message = "info" + i;
                channel.basicPublish(NORMAL_EXCHANGE, "zhangsan", null, message.getBytes());
            }
        }
    }

    /**
     * 队列满了 正常队列 消费者
     */
    static class LengthConsume1 {
        public static void main(String[] args) throws IOException {
            consume();
        }
        private static void consume() throws IOException {
            // 获取信道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明交换机
            channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
            channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);

            // 声明死信队列
            String deadQueue = "dead_queue";
            channel.queueDeclare(deadQueue, false, false, false, null);
            // 绑定交换机队列
            channel.queueBind(deadQueue, DEAD_EXCHANGE, "lisi");

            // 正常队列绑定死信队列
            Map<String, Object> props = new HashMap<>();
            // 设置死信队列交换机
            props.put("x-dead-letter-exchange", DEAD_EXCHANGE);
            // 设置死信队列routing key
            props.put("x-dead-letter-routing-key", "lisi");
            // 设置队列最大长度，消息超过最大长度则成为死信
            props.put("x-max-length", 6);
            // 声明正常队列
            String normalQueue = "normal_queue";
            channel.queueDeclare(normalQueue, false, false, false, props);
            // 绑定交换机队列
            channel.queueBind(normalQueue, NORMAL_EXCHANGE, "zhangsan");
            // 接收消息
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                System.out.println("正常队列消费者 接收到消息" + new String(message.getBody(), "UTF-8"));
            };
            channel.basicConsume(normalQueue, true, deliverCallback, consumerTag -> {});
        }
    }

    /**
     * 队列满了 死信队列 生产者
     */
    static class LengthPublish {
        public static void main(String[] args) throws IOException {
            publish();
        }
        private static void publish() throws IOException {
            // 获取信道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明交换机
            channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
            for (int i = 0; i < 10; i++) {
                String message = "info" + i;
                channel.basicPublish(NORMAL_EXCHANGE, "zhangsan", null, message.getBytes());
            }
        }
    }

    /**
     * 消息过期 正常队列 消费者
     */
    static class TtlConsume {
        public static void main(String[] args) throws IOException {
            consume();
        }
        private static void consume() throws IOException {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明交换机
            channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
            channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);

            // 声明死信队列
            String deadQueue = "dead_queue";
            channel.queueDeclare(deadQueue, false, false, false, null);
            // 绑定交换机队列
            channel.queueBind(deadQueue, DEAD_EXCHANGE, "lisi");

            // 正常队列绑定死信队列
            Map<String, Object> map = new HashMap<>();
            // 正常队列设置死信交换机 key是固定值
            map.put("x-dead-letter-exchange", DEAD_EXCHANGE);
            // 正常队列设置死信队列routing key
            map.put("x-dead-letter-routing-key", "lisi");
            // 正常队列
            String normalQueue = "normal_queue";
            channel.queueDeclare(normalQueue, false, false, false, map);
            // 绑定交换机队列
            channel.queueBind(normalQueue, NORMAL_EXCHANGE, "zhangsan");
            // 接收消息
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                System.out.println("正常队列消费者 接收到消息" + new String(message.getBody(), "UTF-8"));
            };
            channel.basicConsume(normalQueue, true, deliverCallback, consumerTag -> {});
        }
    }

    /**
     * 消息过期生产者
     */
    static class TtlPublish {
        public static void main(String[] args) throws IOException {
            publish();
        }
        private static void publish() throws IOException {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明交换机
            channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
            // 死信消息 设置ttl时间
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();
            for (int i = 0; i < 10; i++) {
                String message = "info" + i;
                channel.basicPublish(NORMAL_EXCHANGE, "zhangsan", properties, message.getBytes());
            }
        }
    }
}
