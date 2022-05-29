package com.cycas.rabbitmq.model;

import com.cycas.rabbitmq.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;

import java.io.IOException;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;

/**
 * 发布确认
 */
public class PublishConfirm {

    public static final String QUEUE_NAME = "confirm_queue";

    public static final int MESSAGE_COUNT = 1000;

    /**
     * 异步发布确认
     */
    static class Async {

        public static void main(String[] args) throws IOException {
            publish();
        }

        private static void publish() throws IOException {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            // 开启发布确认
            channel.confirmSelect();
            /*
             * 线程安全有序的哈希表，适用于高并发
             * 功能：
             * 1.将序号与消息进行key:value关联
             * 2.只要给到序号就能批量删除条目
             * 3.支持高并发(多线程)
             * */
            ConcurrentSkipListMap<Long, String> outstandingConfirms = new ConcurrentSkipListMap<>();
            /**
             * 确认成功回调
             */
            ConfirmCallback confirmCallback = ((deliveryTag, multiple) -> {
                String message = outstandingConfirms.get(deliveryTag);
                if (multiple) {
                    ConcurrentNavigableMap<Long, String> confirmed = outstandingConfirms.headMap(deliveryTag);
                    confirmed.clear();
                } else {
                    outstandingConfirms.remove(deliveryTag);
                }
                System.out.println(String.format("确认的消息tag%s,内容%s", deliveryTag, message));
            });
            /**
             * 确认失败回调
             */
            ConfirmCallback nackCallback = ((deliveryTag, multiple) -> {
                String message = outstandingConfirms.get(deliveryTag);
                System.out.println(String.format("未确认的消息%s,内容%s", deliveryTag, message));
            });
            // 添加监听器
            channel.addConfirmListener(confirmCallback, nackCallback);
            // 开始时间
            long start = System.currentTimeMillis();
            // 循环发送消息
            for (int i = 0; i < MESSAGE_COUNT; i++) {
                String message = i + "";
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                outstandingConfirms.put(channel.getNextPublishSeqNo(), message);
            }
            System.out.println(String.format("发布%s个异步确认消息，耗时%sms", MESSAGE_COUNT, (System.currentTimeMillis() - start)));
        }
    }

    /**
     * 批量发布确认
     */
    static class Batch {

        public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
            publish();
        }

        private static void publish() throws IOException, InterruptedException, TimeoutException {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            // 开启发布确认
            channel.confirmSelect();
            // 开始时间
            long start = System.currentTimeMillis();
            // 批量确认消息大小100
            int batchSize = 100;
            // 循环发送消息
            for (int i = 0; i < MESSAGE_COUNT; i++) {
                String message = i + "";
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                if (i % batchSize == 0) {
                    // 等待确认
                    channel.waitForConfirms();
                }
            }
            System.out.println(String.format("发布%s个单个确认消息，耗时%sms", MESSAGE_COUNT, (System.currentTimeMillis() - start)));
        }

    }

    /**
     * 单个发布确认
     * 这是一种简单的确认方式，它是一种同步确认发布的方式，也就是发布一个消息之后只有它被确认发布，后续的消息才能继续发布
     */
    static class Individually {

        public static void main(String[] args) throws IOException, InterruptedException {
            publish();
        }

        private static void publish() throws IOException, InterruptedException {
            // 获取通道
            Channel channel = RabbitMQUtils.getChannel();
            // 声明队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            // 开启发布确认
            channel.confirmSelect();
            // 开始时间
            long currentTime = System.currentTimeMillis();
            // 循环发送消息
            for (int i = 0; i < MESSAGE_COUNT; i++) {
                String message = i + "";
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                // 等待确认
                if (channel.waitForConfirms()) {
                    System.out.println("消息发送成功");
                }
            }
            System.out.println(String.format("发布%s个单个确认消息，耗时%sms", MESSAGE_COUNT, (System.currentTimeMillis() - currentTime)));
        }

    }
}
