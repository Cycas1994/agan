package com.cycas.netty.chapter2;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author xin.na
 * @since 2024/9/19 14:52
 */
public class IOClient {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();
    }
}
