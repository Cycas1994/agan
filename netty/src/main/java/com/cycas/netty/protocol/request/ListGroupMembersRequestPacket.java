package com.cycas.netty.protocol.request;

import com.cycas.netty.protocol.Packet;
import lombok.Data;

import static com.cycas.netty.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;

/**
 * @author xin.na
 * @since 2024/10/18 9:26
 */
@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
