package com.cycas.netty.client.handler;

import com.cycas.netty.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xin.na
 * @since 2024/10/17 16:30
 */
@ChannelHandler.Sharable
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

    public static final JoinGroupResponseHandler INSTANCE = new JoinGroupResponseHandler();

    private JoinGroupResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println("加入群[" + msg.getGroupId() + "]成功");
        } else {
            System.out.println("加入群[" + msg.getGroupId() + "]失败，原因为：" + msg.getReason());
        }
    }
}
