package com.cycas.netty.protocol.response;

import com.cycas.netty.protocol.Packet;
import lombok.Data;

import static com.cycas.netty.protocol.command.Command.JOIN_GROUP_RESPONSE;

/**
 * @author xin.na
 * @since 2024/10/17 16:21
 */
@Data
public class JoinGroupResponsePacket extends Packet {

    private boolean success;
    private String groupId;
    private String reason;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPONSE;
    }
}
