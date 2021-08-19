package com.my.demo.bean.copy;

/**
 * @author hyl
 * @create 2020-05-15
 * @version:
 */
@FunctionalInterface
public interface BeanUtilCopyCallBack <S,T> {

    /**
     * 定义默认回调方法
     * @param t
     * @param s
     */
    void callBack(S t, T s);
}
