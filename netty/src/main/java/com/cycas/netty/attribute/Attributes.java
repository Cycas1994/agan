package com.cycas.netty.attribute;

import com.cycas.netty.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author xin.na
 * @since 2024/10/9 16:59
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
