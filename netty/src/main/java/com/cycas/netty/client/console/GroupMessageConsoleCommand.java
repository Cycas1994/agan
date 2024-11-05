package com.cycas.netty.client.console;

import com.cycas.netty.protocol.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xin.na
 * @since 2024/10/18 13:46
 */
public class GroupMessageConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入群聊id和消息，空格分开");
        String groupId = scanner.next();
        String message = scanner.next();
        GroupMessageRequestPacket requestPacket = new GroupMessageRequestPacket();
        requestPacket.setToGroupId(groupId);
        requestPacket.setMessage(message);
        channel.writeAndFlush(requestPacket);
    }

}
