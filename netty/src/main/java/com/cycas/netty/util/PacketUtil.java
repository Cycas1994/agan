package com.cycas.netty.util;

import com.cycas.netty.protocol.Packet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xin.na
 * @since 2024/10/14 17:30
 */
public class PacketUtil {

    private static final Map<Byte, Class<? extends Packet>> PACKET_MAP = new HashMap<>();

    static {
        try {
            List<Class<?>> implementations = ReflectionUtil.getAllImplementations(Packet.class);
            for (Class<?> implementation : implementations) {
                Packet packet = (Packet) implementation.newInstance();
                PACKET_MAP.put(packet.getCommand(), (Class<? extends Packet>) implementation);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<? extends Packet> getPacket(Byte command) {
        return PACKET_MAP.get(command);
    }
}
