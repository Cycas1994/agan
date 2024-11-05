package com.cycas.netty.server;

import com.cycas.netty.codec.PacketCodecHandler;
import com.cycas.netty.codec.SpliterDecoder;
import com.cycas.netty.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author xin.na
 * @since 2024/10/9 15:54
 */
public class NettyServer {

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024) // 用于临时存放已完成三次握手请求的队列的最大长度
                .childOption(ChannelOption.SO_KEEPALIVE, true) // 开启TCP底层心跳机制
                .childOption(ChannelOption.TCP_NODELAY, true) // 开启Nagle算法 要求实时性就设置为false 减少发送次数就设置为true
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new IMIdleStateHandler()); // 空闲检测放到最前面，放到后面有可能前面的Inbound出现报错或没有传播导致失效
                        ch.pipeline().addLast(new SpliterDecoder());
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE); // 无需登录，放在AuthHandler前面
                        ch.pipeline().addLast(AuthHandler.INSTANCE);
                        ch.pipeline().addLast(MessageRequestHandler.INSTANCE);
                        ch.pipeline().addLast(CreateGroupRequestHandler.INSTANCE);
                        ch.pipeline().addLast(JoinGroupRequestHandler.INSTANCE);
                        ch.pipeline().addLast(QuitGroupRequestHandler.INSTANCE);
                        ch.pipeline().addLast(ListGroupMembersRequestHandler.INSTANCE);
                        ch.pipeline().addLast(GroupMessageRequestHandler.INSTANCE);
                        ch.pipeline().addLast(LogoutRequestHandler.INSTANCE);
                    }
                });
        bind(serverBootstrap, 8000);

    }

    private static void bind(ServerBootstrap serverBootstrap, int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }
}
