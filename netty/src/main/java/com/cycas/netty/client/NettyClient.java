package com.cycas.netty.client;

import com.cycas.netty.client.console.ConsoleCommandManager;
import com.cycas.netty.client.console.LoginConsoleCommand;
import com.cycas.netty.client.handler.*;
import com.cycas.netty.codec.PacketCodecHandler;
import com.cycas.netty.codec.SpliterDecoder;
import com.cycas.netty.server.handler.IMIdleStateHandler;
import com.cycas.netty.util.SessionUtil;
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
                        ch.pipeline().addLast(new IMIdleStateHandler()); // 空闲检测放到最前面，放到后面有可能前面的Inbound出现报错或没有传播导致失效
                        ch.pipeline().addLast(new SpliterDecoder());
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        ch.pipeline().addLast(new HeartBeatTimerHandler()); // 发送心跳
                        ch.pipeline().addLast(LoginResponseHandler.INSTANCE);
                        ch.pipeline().addLast(MessageResponseHandler.INSTANCE);
                        ch.pipeline().addLast(CreateGroupResponseHandler.INSTANCE);
                        ch.pipeline().addLast(JoinGroupResponseHandler.INSTANCE);
                        ch.pipeline().addLast(QuitGroupResponseHandler.INSTANCE);
                        ch.pipeline().addLast(ListGroupMembersResponseHandler.INSTANCE);
                        ch.pipeline().addLast(GroupMessageResponseHandler.INSTANCE);
                        ch.pipeline().addLast(LogoutResponseHandler.INSTANCE);
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
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        Scanner sc = new Scanner(System.in);

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    new LoginConsoleCommand().exec(sc, channel);
                } else {
                    consoleCommandManager.exec(sc, channel);
                }
            }
        }, "ConsoleThread").start();
    }


}
