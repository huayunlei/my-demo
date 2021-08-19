package com.my.demo.designmode.proxy;

import com.my.demo.designmode.proxy.annotation.RestApi;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 代理类
 * @author hyl
 * @create 2020-05-14
 * @version:
 */
public class MyProxy<T> implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> restApiClass = method.getDeclaringClass();
        System.out.println("restApiClass:"+restApiClass);
        RestApi restApi = AnnotationUtils.getAnnotation(restApiClass, RestApi.class);
        GetMapping getMapping = AnnotationUtils.getAnnotation(method, GetMapping.class);

        try {
            Object ob = restApiClass.getConstructor().newInstance();
            method.invoke(ob, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello";
    }
}
