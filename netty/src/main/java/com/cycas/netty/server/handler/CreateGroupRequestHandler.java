package com.cycas.netty.server.handler;

import com.cycas.netty.protocol.request.CreateGroupRequestPacket;
import com.cycas.netty.protocol.response.CreateGroupResponsePacket;
import com.cycas.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author xin.na
 * @since 2024/10/17 14:52
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> userIdList = msg.getUserIdList();

        List<String> userNameList = new ArrayList<>();
        // 1.创建一个channel分组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        // 2.筛选出待加入群聊的用户的channel和username
        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUsername());
            }
        }

        // 3.群聊创建结果的响应
        String groupId = String.valueOf(new Random().nextInt(10000));
        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);
        responsePacket.setUserNameList(userNameList);

        // 4.给每个客户端都发送拉群通知
        channelGroup.writeAndFlush(responsePacket);

        // 5.保存群组相关信息
        SessionUtil.bindChannelGroup(groupId, channelGroup);

        System.out.print("群创建成功，id为[" + responsePacket.getGroupId() + "]，");
        System.out.println("群里面有：" + responsePacket.getUserNameList());

    }
}
