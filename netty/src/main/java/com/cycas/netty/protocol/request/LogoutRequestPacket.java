package com.cycas.netty.protocol.request;

import com.cycas.netty.protocol.Packet;

import static com.cycas.netty.protocol.command.Command.LOGOUT_REQUEST;

/**
 * @author xin.na
 * @since 2024/10/18 10:43
 */
public class LogoutRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
