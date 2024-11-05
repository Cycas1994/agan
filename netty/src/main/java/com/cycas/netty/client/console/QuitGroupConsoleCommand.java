package com.cycas.netty.client.console;

import com.cycas.netty.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xin.na
 * @since 2024/10/17 16:34
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入groupId，推出群聊：");
        String groupId = scanner.next();

        QuitGroupRequestPacket requestPacket = new QuitGroupRequestPacket();
        requestPacket.setGroupId(groupId);
        channel.writeAndFlush(requestPacket);
    }
}
