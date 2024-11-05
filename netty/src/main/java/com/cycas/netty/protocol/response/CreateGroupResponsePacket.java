package com.cycas.netty.protocol.response;

import com.cycas.netty.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.cycas.netty.protocol.command.Command.CREATE_GROUP_RESPONSE;

/**
 * @author xin.na
 * @since 2024/10/17 15:02
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    private boolean success;
    private String groupId;
    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
