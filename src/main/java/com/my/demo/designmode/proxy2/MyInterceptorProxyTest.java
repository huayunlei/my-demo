package com.my.demo.designmode.proxy2;

/**
 * @author hyl
 * @create 2020-05-20
 * @version:
 */
public class MyInterceptorProxyTest {

    public static void main(String[] args) {
        MyInterceptor myInterceptor = new MyInterceptorImpl();

        MyTarget target = (MyTarget) myInterceptor.register(new MyTargetImpl());
        target.execute1();

        target.execute2();


    }
}
