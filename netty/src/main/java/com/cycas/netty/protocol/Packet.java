package com.cycas.netty.protocol;

import lombok.Data;

/**
 * @author xin.na
 * @since 2024/10/9 15:16
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     * @return
     */
    public abstract Byte getCommand();
}
