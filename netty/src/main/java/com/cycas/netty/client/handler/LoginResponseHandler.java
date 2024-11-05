package com.cycas.netty.client.handler;

import com.cycas.netty.protocol.request.LoginRequestPacket;
import com.cycas.netty.protocol.response.LoginResponsePacket;
import com.cycas.netty.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @author xin.na
 * @since 2024/10/15 14:00
 */
@ChannelHandler.Sharable
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    public static final LoginResponseHandler INSTANCE = new LoginResponseHandler();

    private LoginResponseHandler() {}

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端开始登录");
        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("jack");
        loginRequestPacket.setPassword("pwd");

        // 写数据
//        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive:客户端连接被关闭");
        super.channelInactive(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved:客户端连接被关闭");
        super.handlerRemoved(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println(msg.getUsername() + "，客户端登录成功，userId:" + msg.getUserId());
        } else {
            System.err.println(new Date() + ": 客户端登录失败，原因:" + msg.getReason());
        }
    }
}
