package com.cycas.netty.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * @author xin.na
 * @since 2024/10/9 16:52
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> attr = channel.attr(Attributes.LOGIN);
        return attr.get() != null;
    }
}
