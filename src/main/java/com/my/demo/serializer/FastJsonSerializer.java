package com.my.demo.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * @version:
 */
public class FastJsonSerializer implements TypeSerializer {
    @Override
    public <T> T read(byte[] bytes, String path, Type type) {
        if (StringUtils.isEmpty(path)) {
            return JSON.parseObject(new String(bytes, StandardCharsets.UTF_8), type);
        } else {
            List<String> paths = Arrays.asList(path.split(PATH));
            JSONObject excessive = JSON.parseObject(new String(bytes, StandardCharsets.UTF_8));
            for (int i = 0; i < paths.size(); i++) {
                if (i == paths.size() - 1) {
                    return excessive.getObject(path, type);
                }
                excessive = excessive.getJSONObject(path);
            }
        }
        return null;
    }


    @Override
    public byte[] write(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONBytes(object);
    }
}
