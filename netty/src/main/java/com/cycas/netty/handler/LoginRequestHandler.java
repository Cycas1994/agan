package com.cycas.netty.handler;

import com.cycas.netty.codec.PacketCodeC;
import com.cycas.netty.protocol.LoginRequestPacket;
import com.cycas.netty.protocol.LoginResponsePacket;
import com.cycas.netty.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xin.na
 * @since 2024/10/15 13:48
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        // 登录逻辑
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setVersion(msg.getVersion());
        // 登录校验
        if (this.valid(msg)) {
            System.out.println("登录校验成功!");
            responsePacket.setSuccess(true);
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.err.println("登录校验失败!");
            responsePacket.setSuccess(false);
        }
        ctx.channel().writeAndFlush(responsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
