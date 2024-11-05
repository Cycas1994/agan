package com.cycas.netty.server.handler;

import com.cycas.netty.session.Session;
import com.cycas.netty.protocol.request.MessageRequestPacket;
import com.cycas.netty.protocol.response.MessageResponsePacket;
import com.cycas.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xin.na
 * @since 2024/10/15 13:50
 */
@ChannelHandler.Sharable
public class MessageRequestHandler  extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    private MessageRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        // 1.消息发送方
        Session session = SessionUtil.getSession(ctx.channel());

        // 2.构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUsername());
        messageResponsePacket.setMessage(msg.getMessage());

        // 3.获得消息接收方的Channel
        Channel toUserChannel = SessionUtil.getChannel(msg.getToUserId());

        // 4.发给接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            System.out.println("[" + msg.getToUserId() + "] 不在线，发送失败");
        }
    }
}
