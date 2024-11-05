package com.cycas.netty.protocol.response;

import com.cycas.netty.protocol.Packet;
import lombok.Data;

import static com.cycas.netty.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author xin.na
 * @since 2024/10/9 16:51
 */
@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;
    private String fromUserName;
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
