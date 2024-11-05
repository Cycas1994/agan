package com.cycas.netty.protocol.request;

import com.cycas.netty.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.cycas.netty.protocol.command.Command.CREATE_GROUP_REQUEST;

/**
 * @author xin.na
 * @since 2024/10/17 14:17
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
