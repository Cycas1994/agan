package com.cycas.netty.protocol.response;

import com.cycas.netty.protocol.Packet;
import com.cycas.netty.session.Session;
import lombok.Data;

import java.util.List;

import static com.cycas.netty.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

/**
 * @author xin.na
 * @since 2024/10/18 9:27
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;
    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
