package com.my.demo.designmode.strategy;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 实现 BeanNameAware 接口，为了获得每个processor 在 Spring context 中的 beanName
 * 实现 InitializingBean 接口(这是 Spring 提供的一些生命周期接口中的一个),在 Spring 完成该Bean初始化之后，将 beanName 注册到注册中心去
 * 注意抽象方法 getKeyPair， 这是子类必须实现的，用于构造注册时用的key，即之前说的 “条件”。关于这个 “条件”，有多种实现方式。
 * @author hyl
 * @create 2020-05-21
 * @version:
 */
public abstract class AbstractSmsProcessor implements SmsProcessor, InitializingBean, BeanNameAware {

    @Autowired
    private SmsProcessorRegister register;

    protected String beanName;

    protected abstract SmsProcessorRegister.SmsKeyPair getKayPair();

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        register.put(getKayPair(),beanName);
    }
}
