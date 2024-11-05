package com.cycas.netty.codec;

import com.cycas.netty.protocol.Packet;
import com.cycas.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author xin.na
 * @since 2024/10/15 13:54
 */
@ChannelHandler.Sharable
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    public static final PacketEncoder INSTANCE = new PacketEncoder();

    private PacketEncoder() {}

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodeC.INSTANCE.encode(out, msg);
    }
}
