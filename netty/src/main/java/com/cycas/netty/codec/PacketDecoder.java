package com.cycas.netty.codec;

import com.cycas.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author xin.na
 * @since 2024/10/15 13:41
 */
@ChannelHandler.Sharable
public class PacketDecoder extends ByteToMessageDecoder {

    public static final PacketDecoder INSTANCE = new PacketDecoder();

    private PacketDecoder() {}

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(PacketCodeC.INSTANCE.decode(in));
    }
}
