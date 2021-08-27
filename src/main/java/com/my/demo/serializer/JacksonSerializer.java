package com.my.demo.serializer;

import java.lang.reflect.Type;

/**
 * @version:
 */
public class JacksonSerializer implements TypeSerializer {
    @Override
    public <T> T read(byte[] bytes, String path, Type type) {
        return null;
    }

    @Override
    public byte[] write(Object object) {
        return new byte[0];
    }
}
