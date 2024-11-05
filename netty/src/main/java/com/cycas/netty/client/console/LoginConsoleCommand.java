package com.cycas.netty.client.console;

import com.cycas.netty.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author xin.na
 * @since 2024/10/17 14:40
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入用户名登录:");
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        String username = scanner.nextLine();
        loginRequestPacket.setUsername(username);
        loginRequestPacket.setPassword("pwd");

        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {

        }
    }
}
