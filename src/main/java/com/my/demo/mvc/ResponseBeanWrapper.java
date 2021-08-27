package com.my.demo.mvc;

import org.springframework.core.MethodParameter;

/**
 * @version:
 */
public interface ResponseBeanWrapper {

    boolean supportsType(MethodParameter returnType);

    Object wrap(MethodParameter returnType, Object bean);

}
