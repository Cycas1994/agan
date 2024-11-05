package com.cycas.netty.client.console;

import com.cycas.netty.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author xin.na
 * @since 2024/10/17 14:16
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket request = new CreateGroupRequestPacket();
        System.out.println("【拉人群聊】输入userId列表，userId之间英文逗号隔开：");
        String userIds = scanner.next();
        request.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITER)));
        channel.writeAndFlush(request);
    }
}
