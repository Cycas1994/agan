package com.cycas.netty.protocol.request;

import com.cycas.netty.protocol.Packet;

import static com.cycas.netty.protocol.command.Command.HEARTBEAT_REQUEST;

/**
 * @author xin.na
 * @since 2024/10/25 14:42
 */
public class HeartBeatRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
