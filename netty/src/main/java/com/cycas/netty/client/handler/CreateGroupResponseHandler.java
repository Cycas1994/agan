package com.cycas.netty.client.handler;

import com.cycas.netty.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xin.na
 * @since 2024/10/17 15:01
 */
@ChannelHandler.Sharable
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    public static final CreateGroupResponseHandler INSTANCE = new CreateGroupResponseHandler();

    private CreateGroupResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception {
        System.out.print("群创建成功，id为[" + msg.getGroupId() + "]，");
        System.out.println("群里面有：" + msg.getUserNameList());
    }
}
