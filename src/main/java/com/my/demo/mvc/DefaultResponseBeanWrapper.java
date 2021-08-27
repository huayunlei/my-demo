package com.my.demo.mvc;

import org.springframework.core.MethodParameter;

/**
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
