package com.my.demo.serializer;

import java.lang.reflect.Type;

/**
 * 指定类型序列化
 * @version:
 */
public interface TypeSerializer {

    String PATH = "/";

    /**
     * 将字节数反序列化为一个对象
     * @param bytes 字节数组
     * @param path 路径
     * @param type 目标对象类型
     */
    <T> T read(byte[] bytes, String path, Type type);

    /**
     * 将对象序列化为字节数组
     * @param object 对象
     */
    byte[] write(Object object);

    /**
     * 不带路径的反序列化
     * @param bytes 字节数组
     * @param type 目标对象类型
     */
    default <T> T read(byte[] bytes, Type type) {
        return read(bytes, null, type);
    }

}
