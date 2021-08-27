package com.my.demo.mvc;

import org.springframework.core.MethodParameter;

/**
 * 默认返回处理（兜底）
 * @version:
 */
public class DefaultResponseBeanWrapper implements ResponseBeanWrapper {

    @Override
    public boolean supportsType(MethodParameter returnType) {
        return true;
    }

    @Override
    public Object wrap(MethodParameter returnType, Object bean) {
        return StandardResponse.success(bean);
    }
}
