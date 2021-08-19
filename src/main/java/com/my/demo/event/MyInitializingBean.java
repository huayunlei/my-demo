package com.my.demo.event;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author 72084513
 * @create 2021-07-27
 * @version:
 */
public class MyInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ceshi InitializingBean");
    }
}
