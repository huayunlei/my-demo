package com.my.demo.bean.ioc;

/**
 * @version:
 */
public interface BeanMatcher<T> {

    boolean match(T factor);

}
