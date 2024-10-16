package com.cycas.netty.chapter2;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xin.na
 * @since 2024/9/19 14:44
 */
public class IOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);

        // 接收新连接线程
        new Thread(() -> {
            while (true) {
                try {
                    // 阻塞获取新连接
                    Socket socket = serverSocket.accept();
                    // 为每一个连接都创建一个新线程
                    new Thread(() -> {
                        int len;
                        byte[] data = new byte[1024];
                        try {
                            InputStream inputStream = socket.getInputStream();
                            // 按字节流方式读取数据
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }
                        } catch (IOException e) {
                        }
                    }).start();
                } catch (IOException e) {
                }
            }
        }).start();
    }
}
