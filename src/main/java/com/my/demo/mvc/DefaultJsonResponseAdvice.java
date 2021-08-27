package com.my.demo.mvc;

import com.my.demo.serializer.FastJsonSerializer;
import com.my.demo.serializer.TypeSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Arrays;
import java.util.List;

/**
 * 只能统一处理@ResponseBody注解的返回值
 * @version:
 */
//@Configuration
//@ControllerAdvice(basePackages = "com.my.demo")
public class DefaultJsonResponseAdvice implements ResponseBodyAdvice<Object> {

    private TypeSerializer typeSerializer = new FastJsonSerializer();

    private List<ResponseBeanWrapper> responseBeanWrappers = Arrays.asList(new DefaultResponseBeanWrapper());

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(ResponseBody.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType
            , Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Object responseBody = body;
        if (response == null) {

        }

        for (ResponseBeanWrapper responseBeanWrapper : responseBeanWrappers) {
            if (responseBeanWrapper.supportsType(returnType)) {
                responseBody = responseBeanWrapper.wrap(returnType, responseBody);
                break;
            }
        }

        return responseBody;
    }
}
