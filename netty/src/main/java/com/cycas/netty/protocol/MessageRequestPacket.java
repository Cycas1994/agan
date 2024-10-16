package com.cycas.netty.protocol;

import lombok.Data;

import static com.cycas.netty.protocol.Command.MESSAGE_REQUEST;

/**
 * @author xin.na
 * @since 2024/10/9 16:51
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
