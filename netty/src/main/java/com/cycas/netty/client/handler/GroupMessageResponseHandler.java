package com.cycas.netty.client.handler;

import com.cycas.netty.protocol.response.GroupMessageResponsePacket;
import com.cycas.netty.session.Session;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xin.na
 * @since 2024/10/18 16:09
 */
@ChannelHandler.Sharable
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    public static final GroupMessageResponseHandler INSTANCE = new GroupMessageResponseHandler();

    private GroupMessageResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket msg) throws Exception {
        String fromGroupId = msg.getFromGroupId();
        Session fromUser = msg.getFromUser();
        System.out.println("收到群[" + fromGroupId + "]中[" + fromUser.getUsername() + "]发来的消息:" + msg.getMessage());
    }

}
