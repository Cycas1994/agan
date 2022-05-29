package com.cycas.rabbitmq.model.prototype;

import com.cycas.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import sun.misc.PostVMInitHook;

import java.util.Scanner;

/**
 * Work queues，也被称为（Task queues），任务模型。
 * 当消息处理比较耗时的时候，可能生产消息的速度会远远大于消息的消费速度。
 * 长此以往，消息就会堆积越来越多，无法及时处理。
 * 此时就可以使用work 模型：让多个消费者绑定到一个队列，共同消费队列中的消息。
 * 队列中的消息一旦消费，就会消失，因此任务是不会被重复执行的。
 */
public class WorkQueues {

    // 队列名称
    public static final String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception {
//        WorkPublish.publish();

        new Thread(() -> {
            try {
                WorkConsumer.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                WorkConsumer.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    static class WorkConsumer {

        private static void consume() throws Exception {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 接收消息回调
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                System.out.println(Thread.currentThread().getName() + "接收到的消息" + new String(message.getBody()));
            };
            // 取消消息回调
            CancelCallback cancelCallback = consumerTag -> {
                System.out.println(consumerTag + "：消息取消");
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

    static class WorkPublish {

        private static void publish() throws Exception {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 从控制台接收消息
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
