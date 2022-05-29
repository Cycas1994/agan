package com.cycas.rabbitmq.model;

import com.cycas.rabbitmq.util.RabbitMQUtils;
import com.cycas.rabbitmq.util.SleepUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.Scanner;

/**
 * 消息应答
 * 消费者在接收到消息并且处理该消息之后，告诉 rabbitmq 它已经处理了，rabbitmq 可以把该消息删除了
 * 消息应答分为：自动应答和手动应答
 */
public class MessageAck {

    // 队列名称
    private static final String QUEUE_NAME = "ack_queue";


    static class AckConsumer {
        public static void main(String[] args) throws Exception {
            consume();
        }

        private static void consume() throws Exception {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 接收消息回调
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                System.out.println(Thread.currentThread().getName() + "睡眠5s");
                SleepUtils.sleep(5);
                System.out.println(Thread.currentThread().getName() + "接收消息" + new String(message.getBody()));
                /* 手动应答
                 * 1.消息的标记 tag
                 * 2.是否批量应答
                 **/
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            };
            // 消息取消回调
            CancelCallback cancelCallback = consumerTag -> {
                System.out.println(consumerTag + "消费者取消消费");
            };
            /*
             * 消费者消费消息
             * 1.消费哪个队列
             * 2.消费成功之后是否要自动应答 true自动应答 false手动应答
             * 3.消费者未成功消费的回调
             * 4.消费者取消消费的回调
             * */
            channel.basicConsume(QUEUE_NAME, false, deliverCallback, cancelCallback);
        }
    }

    static class AckPublish {
        public static void main(String[] args) throws Exception {
            publish();
        }

        private static void publish() throws Exception {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            /*
             * 声明队列
             * 1.队列名称
             * 2.队列里面的消息是否持久化（存储在磁盘），默认情况消息存储在内存中
             * 3.该队列是否只供一个消费者进行消费，是否进行消息共享。true可以多个消费者消费，false只能一个消费者消费
             * 4.最后一个消费者端开链接以后该队列是否自动删除 true自动删除 false不自动删除
             * */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
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
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            }
        }

    }
}
