package com.my.demo.bean.ioc;

/**
 * @version:
 */
public interface MapableBeanMatcher<K> extends BeanMatcher<K> {

    K mapKey();

    @Override
    default boolean match(K factor) {
        return factor.equals(mapKey());
    }
}
