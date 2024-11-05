package com.cycas.netty.client.handler;

import com.cycas.netty.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xin.na
 * @since 2024/10/17 16:40
 */
@ChannelHandler.Sharable
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    public static final QuitGroupResponseHandler INSTANCE = new QuitGroupResponseHandler();

    private QuitGroupResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println("退出群[" + msg.getGroupId() + "]成功");
        } else {
            System.out.println("退出群[" + msg.getGroupId() + "]失败，失败原因为：" + msg.getReason());
        }
    }
}
