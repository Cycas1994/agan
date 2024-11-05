package com.cycas.netty.client.console;

import com.cycas.netty.protocol.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xin.na
 * @since 2024/10/18 10:43
 */
public class LogoutConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket requestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(requestPacket);
    }
}
