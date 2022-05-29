package com.cycas.rabbitmq.model;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * hello world简单工作模式
 * P：生产者，也就是要发送消息的程序
 * C：消费者：消息的接受者，会一直等待消息到来。
 * queue：消息队列
 */
public class SimpleQueues {

    // 队列名称
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 消费消息
        SimpleConsumer.consume(factory);
    }

    static class SimpleConsumer {

        private static void consume(ConnectionFactory factory) throws IOException, TimeoutException {
            // 创建连接
            Connection connection = factory.newConnection();
            // 获取通道
            Channel channel = connection.createChannel();
            // 接收消息回调
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                System.out.println(String.format("tag:%s", consumerTag));
                System.out.println(new String(message.getBody()));
            };
            // 取消消息回调
            CancelCallback cancelCallback = consumerTag -> {
                System.out.println(String.format("tag:%s", consumerTag));
                System.out.println("消息消费被中断");
            };
            /*
             * 消费者消费消息
             * 1.消费哪个队列
             * 2.消费成功之后是否要自动应答 true自动应答 false手动应答
             * 3.消费者成功消费的回调
             * 4.消费者取消消费的回调
             * */
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
        }
    }

    static class SimpleProducer {

        public static void main(String[] args) throws IOException, TimeoutException {
            // 创建一个连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            // 生产消息
            SimpleProducer.publish(factory);
        }
        private static void publish(ConnectionFactory factory) throws IOException, TimeoutException {
            // 创建连接
            Connection connection = factory.newConnection();
            // 获取通道
            Channel channel = connection.createChannel();
            /*
             * 生成一个队列
             * 1.队列名称
             * 2.队列里面的消息是否持久化（存储在磁盘），默认情况消息存储在内存中
             * 3.该队列是否只供一个消费者进行消费，是否进行消息共享。true可以多个消费者消费，false只能一个消费者消费
             * 4.最后一个消费者端开链接以后该队列是否自动删除 true自动删除 false不自动删除
             * */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 发消息
            String message = "hello world";
            /*
             * 发送一次消费
             * 1.发送到哪个交换机
             * 2.路由的key值是哪个 本次是队列的名称
             * 3.其他参数信息
             * 4.发送消息的消息体
             * */
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        }
    }

}


