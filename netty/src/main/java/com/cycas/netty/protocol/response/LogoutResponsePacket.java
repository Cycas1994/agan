package com.cycas.netty.protocol.response;

import com.cycas.netty.protocol.Packet;
import lombok.Data;

import static com.cycas.netty.protocol.command.Command.LOGOUT_RESPONSE;

/**
 * @author xin.na
 * @since 2024/10/18 10:45
 */
@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}
