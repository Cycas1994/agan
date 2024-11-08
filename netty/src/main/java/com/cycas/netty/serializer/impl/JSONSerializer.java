package com.cycas.netty.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.cycas.netty.serializer.Serializer;
import com.cycas.netty.serializer.SerializerAlgorithm;

/**
 * @author xin.na
 * @since 2024/10/9 15:25
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
