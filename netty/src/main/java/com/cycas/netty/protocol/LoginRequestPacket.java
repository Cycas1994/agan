package com.cycas.netty.protocol;

import com.cycas.netty.protocol.Packet;
import lombok.Data;

import static com.cycas.netty.protocol.Command.LOGIN_REQUEST;

/**
 * @author xin.na
 * @since 2024/10/9 15:21
 */
@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
