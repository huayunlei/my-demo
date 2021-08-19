package com.my.demo.designmode.proxy2;

import com.my.demo.designmode.proxy2.annotation.TypeName;

/**
 * @author hyl
 * @create 2020-05-20
 * @version:
 */
@TypeName("execute1")
public class MyInterceptorImpl implements MyInterceptor {
    @Override
    public Object intercept(MyInvocation invocation) throws Throwable {
        System.out.println("Go Go Go!!!");
        return invocation.proceed();
    }

    @Override
    public Object register(Object target) {
        return MyTargetProxy.bind(target, this);
    }
}
