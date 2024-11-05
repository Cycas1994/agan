package com.cycas.netty.client.console;

import com.cycas.netty.protocol.request.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xin.na
 * @since 2024/10/18 9:25
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入groupId，获取群成员列表：");
        String groupId = scanner.next();
        ListGroupMembersRequestPacket requestPacket = new ListGroupMembersRequestPacket();
        requestPacket.setGroupId(groupId);
        channel.writeAndFlush(requestPacket);
    }
}
