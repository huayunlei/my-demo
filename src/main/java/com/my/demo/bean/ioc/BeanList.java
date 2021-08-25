package com.my.demo.bean.ioc;

import java.util.List;
import java.util.Optional;

/**
 * @version:
 */
public interface BeanList<K, E extends BeanMatcher<K>> extends List<E> {
    /**
     * 根据因子获取E实例
     * @param factor 因子
     * @return E实例 如果不存在返回null
     */
    E find(K factor);

    /**
     * 根据因子获取E实例的Optional对象
     * @param factor 因子
     * @return E实例Optional对象
     */
    Optional<E> optional(K factor);

    /**
     * 根据银子获取E实例列表
     * @param factor 因子
     * @return E实例列表
     */
    List<E> list(K factor);

    /**
     * 根据组别获取E实例列表
     * @param group 组别
     * @return E实例列表
     */
    List<E> group(Class<?> group);

    /**
     * 根据组别获取E实例的Optional对象
     * @param group 组别
     * @return 实例列表
     */
    Optional<E> groupOptional(Class<?> group);

    /**
     * 根据组别获取E实例的Optional对象
     * @param group 组别
     * @return E实例Optional对象
     */
    E groupOne(Class<?> group);

    /**
     * 根据key获取E实例
     * @param key key
     * @return E实例 如果不存在返回null
     */
    E fetchOne(K key);

    /**
     * 根据因子获取E实例的Optional对象
     * @param key key
     * @return E实例Optional对象
     */
    Optional<E> fetchOptional(K key);
}
