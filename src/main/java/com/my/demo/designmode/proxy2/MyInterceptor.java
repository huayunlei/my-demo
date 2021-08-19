package com.my.demo.designmode.proxy2;

import java.lang.reflect.InvocationTargetException;

/**
 * @author hyl
 * @create 2020-05-20
 * @version:
 */
public interface MyInterceptor {

    Object intercept(MyInvocation invocation) throws Throwable;

    Object register(Object target);
}
