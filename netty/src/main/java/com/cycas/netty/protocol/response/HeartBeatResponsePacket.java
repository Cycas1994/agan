package com.cycas.netty.protocol.response;

import com.cycas.netty.protocol.Packet;

import static com.cycas.netty.protocol.command.Command.HEARTBEAT_RESPONSE;

/**
 * @author xin.na
 * @since 2024/10/25 14:56
 */
public class HeartBeatResponsePacket extends Packet {

    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
