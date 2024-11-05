package com.cycas.netty.protocol.request;

import com.cycas.netty.protocol.Packet;
import lombok.Data;

import static com.cycas.netty.protocol.command.Command.GROUP_MESSAGE_REQUEST;

/**
 * @author xin.na
 * @since 2024/10/18 13:48
 */
@Data
public class GroupMessageRequestPacket extends Packet {

    private String toGroupId;
    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}
