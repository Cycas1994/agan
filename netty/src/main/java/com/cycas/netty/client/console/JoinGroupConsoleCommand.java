package com.cycas.netty.client.console;

import com.cycas.netty.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xin.na
 * @since 2024/10/17 15:50
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入groupId，加入群聊：");
        String groupId = scanner.next();

        JoinGroupRequestPacket requestPacket = new JoinGroupRequestPacket();
        requestPacket.setGroupId(groupId);
        channel.writeAndFlush(requestPacket);
    }
}
