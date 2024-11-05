package com.cycas.netty.client.handler;

import com.cycas.netty.protocol.response.LogoutResponsePacket;
import com.cycas.netty.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xin.na
 * @since 2024/10/18 10:55
 */
@ChannelHandler.Sharable
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    public static final LogoutResponseHandler INSTANCE = new LogoutResponseHandler();

    private LogoutResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            SessionUtil.unbindSession(ctx.channel());
            System.out.println("登出成功");
            ctx.close();
        } else {
            System.out.println("登出失败");
        }
    }
}
