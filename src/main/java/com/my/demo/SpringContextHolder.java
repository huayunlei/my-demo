package com.my.demo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author hyl
 * @create 2020-05-21
 * @version:
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;

    public SpringContextHolder() {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.context = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) context.getBean(name);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name, Object... arg1) {
        return (T) context.getBean(name, arg1);
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return context.getBean(name, clazz);
    }

}
