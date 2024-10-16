package com.cycas.netty.protocol;

import lombok.Data;

import static com.cycas.netty.protocol.Command.LOGIN_RESPONSE;

/**
 * @author xin.na
 * @since 2024/10/9 16:37
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
