package com.cycas.netty.serializer;

/**
 * @author xin.na
 * @since 2024/10/9 15:22
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * java对象转成二进制数据
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制数据转成java对象
     * @param clazz
     * @param bytes
     * @return
     * @param <T>
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
