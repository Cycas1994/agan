package com.cycas.netty.handler;

import com.cycas.netty.codec.PacketCodeC;
import com.cycas.netty.protocol.MessageRequestPacket;
import com.cycas.netty.protocol.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author xin.na
 * @since 2024/10/15 13:50
 */
public class MessageRequestHandler  extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        System.out.println(new Date() + "：收到客户端消息："+ msg.getMessage());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复[" + msg.getMessage() + "]");
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
