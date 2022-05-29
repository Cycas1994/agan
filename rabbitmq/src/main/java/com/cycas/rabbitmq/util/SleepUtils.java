package com.cycas.rabbitmq.util;

/**
 * 睡眠工具类
 */
public class SleepUtils {

    public static void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
        }
    }
}
