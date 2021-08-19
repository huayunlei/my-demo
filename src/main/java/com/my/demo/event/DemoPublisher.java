package com.my.demo.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author hyl
 * @create 2020-07-30
 * @version: branch_member_20200601_v3_7_1
 */
@Component
public class DemoPublisher {

    @Autowired
    private ApplicationContext context;

    public void publish(String msg) {
        context.publishEvent(new DemoEvent(msg));
    }
}
