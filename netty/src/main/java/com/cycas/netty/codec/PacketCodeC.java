package com.cycas.netty.codec;

import com.cycas.netty.protocol.*;
import com.cycas.netty.serializer.Serializer;
import com.cycas.netty.util.PacketUtil;
import com.cycas.netty.util.ReflectionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xin.na
 * @since 2024/10/9 15:29
 */
public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x12345678;

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private PacketCodeC() {}
    /**
     * +----------------------------------------------------------+
     * |  魔数  |  版本号  |  序列化算法  |  指令  |  数据长度  |  数据  |
     * +----------------------------------------------------------+
     * 4字节     1字节       1字节       1字节     4字节      N字节
     *
     * @param packet
     * @return
     */
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        // 1.序列化Java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 2. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过魔数
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = this.getRequestType(command);
        Serializer serializer = this.getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return PacketUtil.getPacket(command);
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        if (serializeAlgorithm == Serializer.DEFAULT.getSerializerAlgorithm()) {
            return Serializer.DEFAULT;
        }
        return null;
    }

}
