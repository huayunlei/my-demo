package com.my.demo.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Spring 提供了一个SmartApplicationListener类，可以支持listener之间的触发顺序，普通的ApplicationListener优先级最低（最后触发）
 */
@Component
public class MyOrderApplicationListener implements SmartApplicationListener {
    // 对于web应用会出现父子容器，这样就会触发两次
    private volatile AtomicBoolean isInit=new AtomicBoolean(false);

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == ContextRefreshedEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return true;
    }

    /**
    * 值越小，就先触发
    * */
    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        //防止重复触发
        if(!isInit.compareAndSet(false,true)) {
            return;
        }

        System.out.println("MyOrderApplicationListener init。。。。。。。。。。。。。。。");

    }
}
