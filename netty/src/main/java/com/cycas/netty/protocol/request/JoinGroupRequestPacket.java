package com.cycas.netty.protocol.request;

import com.cycas.netty.protocol.Packet;
import lombok.Data;

import static com.cycas.netty.protocol.command.Command.JOIN_GROUP_REQUEST;

/**
 * @author xin.na
 * @since 2024/10/17 15:50
 */
@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }
}
