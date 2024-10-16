package com.cycas.netty.util;

import io.netty.util.AttributeKey;

/**
 * @author xin.na
 * @since 2024/10/9 16:59
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");


}
