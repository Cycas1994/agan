package com.cycas.netty.server.handler;

import com.cycas.netty.protocol.request.HeartBeatRequestPacket;
import com.cycas.netty.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xin.na
 * @since 2024/10/25 14:55
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler  extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    private HeartBeatRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket msg) throws Exception {
        ctx.channel().writeAndFlush(new HeartBeatResponsePacket());
    }
}
