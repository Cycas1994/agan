package com.cycas.netty.client;

import com.cycas.netty.codec.PacketDecoder;
import com.cycas.netty.codec.PacketEncoder;
import com.cycas.netty.codec.SpliterDecoder;
import com.cycas.netty.handler.LoginResponseHandler;
import com.cycas.netty.handler.MessageResponseHandler;
import com.cycas.netty.protocol.MessageRequestPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author xin.na
 * @since 2024/10/9 16:06
 */
public class NettyClient {

    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 1.指定线程模型
                .group(workerGroup)
                // 2.指定IO类型为NIO
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000) // 连接超时时间
                .option(ChannelOption.SO_KEEPALIVE, true) // 开启TCP底层心跳机制
                .option(ChannelOption.TCP_NODELAY, true) // 开启Nagle算法 要求实时性就设置为false 减少发送次数就设置为true
                // 3.IO处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new SpliterDecoder());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        // 4.建立连接
        connect(bootstrap, "127.0.0.1", 8000, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接!");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连...");
                bootstrap.config().group().schedule(
                        () -> connect(bootstrap, host, port, retry - 1),
                        delay,
                        TimeUnit.SECONDS);
            }
        });
    }
    
    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
//                if (LoginUtil.hasLogin(channel)) {
                    System.out.println("输入消息发送至服务端：");
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.nextLine();

                    MessageRequestPacket packet = new MessageRequestPacket();
                    packet.setMessage(line);
                    channel.writeAndFlush(packet);
//                }
            }
        }, "ConsoleThread").start();
    }
}
