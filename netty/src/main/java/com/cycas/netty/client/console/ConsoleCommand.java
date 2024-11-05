package com.cycas.netty.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xin.na
 * @since 2024/10/17 14:01
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);

}
