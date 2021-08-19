package com.my.demo.designmode.factory;

import java.util.List;

/**
 * 抽象出来的通用工厂方法
 * @author hyl
 * @create 2020-05-13
 * @version:
 */
public abstract class AbstractCommFacadeFactory<T extends TypeSupport, E> {

    /**
     * 工厂获取子类实现
     *
     * @param type 类型
     * @return 具体实现
     */
    public T get(E type) {
        List<T> services = this.getServices();
        if (null == services || services.isEmpty() || null == type) {
            return null;
        }

        for (T service : services) {
            if (null == service) {
                continue;
            }
            if (type == service.getType()) {
                return service;
            }
        }
        return null;
    }

    /**
     * 子类列表
     *
     * @return 子类列表
     */
    public abstract List<T> getServices();

}
