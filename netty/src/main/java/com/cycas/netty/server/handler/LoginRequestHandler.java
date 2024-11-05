package com.cycas.netty.server.handler;

import com.cycas.netty.protocol.request.LoginRequestPacket;
import com.cycas.netty.protocol.response.LoginResponsePacket;
import com.cycas.netty.session.Session;
import com.cycas.netty.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @author xin.na
 * @since 2024/10/15 13:48
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        // 登录逻辑
        System.out.println(msg.getUsername() + "，登录成功!");

        String userId = randomUserId();
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setVersion(msg.getVersion());
        responsePacket.setUsername(msg.getUsername());

        if (valid(msg)) {
            responsePacket.setSuccess(true);
            responsePacket.setUserId(userId);

            SessionUtil.bindSession(new Session(userId, msg.getUsername()), ctx.channel());
            System.out.println(new Date() + "，用户：" + userId + "[" + msg.getUsername() + "]" + "，登录成功!");
        } else {
            responsePacket.setReason("校验失败");
            responsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }
        // 响应
        ctx.channel().writeAndFlush(responsePacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unbindSession(ctx.channel());
        super.channelInactive(ctx);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    private String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

}
