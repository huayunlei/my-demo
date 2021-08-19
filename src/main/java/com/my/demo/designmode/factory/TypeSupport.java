package com.my.demo.designmode.factory;

/**
 *
 *  工厂类型接口
 *  用来匹配实现类 注：泛型限定枚举类型，更改需要注意值比对的方式
 * @author hyl
 * @create 2020-05-13
 * @version:
 */
public interface TypeSupport<E extends Enum> {

    E getType();
}
