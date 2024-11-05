package com.cycas.netty.protocol.response;

import com.cycas.netty.protocol.Packet;
import com.cycas.netty.session.Session;
import lombok.Data;

import static com.cycas.netty.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

/**
 * @author xin.na
 * @since 2024/10/18 15:56
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;
    private String message;
    private Session fromUser;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
