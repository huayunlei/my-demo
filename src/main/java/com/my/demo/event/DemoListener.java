package com.my.demo.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author hyl
 * @create 2020-07-30
 * @version: branch_member_20200601_v3_7_1
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        String msg = demoEvent.getMsg();
        System.out.println("DemoListener 接收到消息 ： "+msg);
    }
}
