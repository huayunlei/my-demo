package com.my.demo.designmode.proxy;

import com.my.demo.designmode.proxy.annotation.RestApi;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 注册spring容器
 * @author hyl
 * @create 2020-05-14
 * @version:
 */
@Component
public class MyRegistryBean implements ApplicationContextAware, BeanDefinitionRegistryPostProcessor {

    private ApplicationContext act;
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        // 01 获取指定包下的所有类类型
        Reflections reflections = new Reflections("com.my.demo.designmode.proxy");

//        // 02 获取子类的类类型
//        Set<Class<? extends IStudent>> subTypesOf = reflections
//                .getSubTypesOf(IStudent.class);

        // 02 获取标注了@RestApi的类的类类型
        Set<Class<?>> typesAnnotatedWith = reflections
                .getTypesAnnotatedWith(RestApi.class);

        // 03 遍历输出
        for (Class cls : typesAnnotatedWith) {
            System.out.println("-------------------------------------");
            System.out.println("SimpleName:" + cls.getSimpleName());

            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(cls);
            GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
            definition.getPropertyValues().add("interfaceClass", definition.getBeanClassName());
            definition.setBeanClass(MyProxyFactory.class);
            definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
            // 注册bean名,一般为类名首字母小写
            beanDefinitionRegistry.registerBeanDefinition(cls.getSimpleName(), definition);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.act = applicationContext;
    }
}
