package com.my.demo.designmode.proxy2;

import com.my.demo.designmode.proxy2.annotation.TypeName;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author hyl
 * @create 2020-05-20
 * @version:
 */
public class MyTargetProxy implements InvocationHandler {

    private Object target;
    private MyInterceptor interceptor;

    private MyTargetProxy(Object target, MyInterceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    //将拦截逻辑封装到拦截器中，有客户端生成目标类的代理类的时候一起传入，这样客户端就可以设置不同的拦截逻辑。
    public static Object bind(Object target, MyInterceptor interceptor) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new MyTargetProxy(target, interceptor));
    }

    public Object invoke(Object proxy, Method method,
                         Object[] args) throws Throwable {
        TypeName typeName = this.interceptor.getClass().getAnnotation(TypeName.class);
        System.out.println(typeName != null ? typeName.toString() : " typeName is null ");
        if (typeName == null) {
            throw new NullPointerException("xxxx");
        }
        //如果注解上的方法名和该方法名一样，才拦截
        String name = typeName.value();
        if (name.equals(method.getName())) {
            return interceptor.intercept(new MyInvocation(target, method, args));
        }

        return method.invoke(target, args);
    }
}
