package com.cycas.netty.protocol;

/**
 * @author xin.na
 * @since 2024/10/9 15:20
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;
}
