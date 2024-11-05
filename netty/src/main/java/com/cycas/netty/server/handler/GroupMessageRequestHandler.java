package com.cycas.netty.server.handler;

import com.cycas.netty.protocol.request.GroupMessageRequestPacket;
import com.cycas.netty.protocol.response.GroupMessageResponsePacket;
import com.cycas.netty.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author xin.na
 * @since 2024/10/18 15:55
 */
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    private GroupMessageRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket msg) throws Exception {
        // 1.拿到groupId构造群聊消息的响应
        String groupId = msg.getToGroupId();
        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        responsePacket.setFromGroupId(groupId);
        responsePacket.setMessage(msg.getMessage());
        responsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));

        // 2.拿到群聊对应的ChannelGroup
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.writeAndFlush(responsePacket);
    }
}
