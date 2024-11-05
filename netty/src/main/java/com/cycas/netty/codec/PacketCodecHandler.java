package com.cycas.netty.codec;

import com.cycas.netty.protocol.Packet;
import com.cycas.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xin.na
 * @since 2024/10/23 16:53
 */
@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();

    private PacketCodecHandler() {}

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        PacketCodeC.INSTANCE.encode(byteBuf, msg);
        out.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        out.add(PacketCodeC.INSTANCE.decode(msg));
    }

    public static void main(String[] args) {
//        10704 277 32773
        List<Person> list = new ArrayList<>();
        Person person1 = new Person("zs", 10704L);
        Person person2 = new Person("zs", 277L);
        Person person3 = new Person("zs", 32773L);
        list.add(person1);
        list.add(person2);
        list.add(person3);
        System.out.println("排序前:" + list);
        List<Person> listNew = list.stream().sorted(Comparator.comparing(Person::getFirstOrgName)
                .thenComparing(Person::getSecondOrgId)).collect(Collectors.toList());
        System.out.println("排序后:" + listNew);
    }

    @Data
    @AllArgsConstructor
    public static class Person {
        private String firstOrgName;
        private Long secondOrgId;
    }
}
