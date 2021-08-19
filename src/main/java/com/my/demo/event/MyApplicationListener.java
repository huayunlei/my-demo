package com.my.demo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * InitializingBean的时机是初始化bean的时候，而ApplicationListener<ContextRefreshedEvent>是在bean初始化完成后
 * 也就是先有InitializingBean（当执行完默认的构造函数之后，就会调用该类实现afterPropertiesSet方法），
 * 后有ApplicationListener<ContextRefreshedEvent>
 *
 */
@Slf4j
@Component
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    // 对于web应用会出现父子容器，这样就会触发两次
    private volatile AtomicBoolean isInit=new AtomicBoolean(false);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //防止重复触发
        if(!isInit.compareAndSet(false,true)) {
            return;
        }

        System.out.println("容器中初始化Bean数量:"+contextRefreshedEvent.getApplicationContext().getBeanDefinitionCount());

        // root application context
//        if(null == contextRefreshedEvent.getApplicationContext().getParent()) {
//            log.debug(">>>>> spring初始化完毕 <<<<<");
//            // spring初始化完毕后，通过反射调用所有使用BaseService注解的initMapper方法
//            Map<String, Object> baseServices =
//                    contextRefreshedEvent.getApplicationContext().
//                            getBeansWithAnnotation(BaseService.class);
//            for(Object service : baseServices.values()) {
//                log.debug(">>>>> {}.initMapper()", service.getClass().getName());
//                try {
//                    Method initMapper = service.getClass().getMethod("initMapper");
//                    initMapper.invoke(service);
//                } catch (Exception e) {
//                    log.error("初始化BaseService的initMapper方法异常", e);
//                    e.printStackTrace();
//                }
//            }
//
//            // 系统入口初始化，业余草：www.xttblog.com
//            Map<String, BaseInterface> baseInterfaceBeans =
//                    contextRefreshedEvent.getApplicationContext().
//                            getBeansOfType(BaseInterface.class);
//            for(Object service : baseInterfaceBeans.values()) {
//                log.debug(">>>>> {}.init()", service.getClass().getName());
//                try {
//                    Method init = service.getClass().getMethod("init");
//                    init.invoke(service);
//                } catch (Exception e) {
//                    log.error("初始化BaseInterface的init方法异常", e);
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}
