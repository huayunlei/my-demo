package com.my.demo.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version:
 */
public class MvcConfiguration {

    @Bean
    public ArgumentResolverReturnValueRegister argumentResolverReturnValueRegister(
            JsonMethodReturnValueHandler jsonMethodReturnValueHandler, RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        ArgumentResolverReturnValueRegister register = new ArgumentResolverReturnValueRegister();

        register.setAdapter(requestMappingHandlerAdapter);
        register.setReturnValueHandlers(Arrays.asList(jsonMethodReturnValueHandler));

        return register;
    }

    @Bean
    public JsonMethodReturnValueHandler jsonMethodReturnValueHandler() {
        JsonMethodReturnValueHandler jsonMethodReturnValueHandler = new JsonMethodReturnValueHandler();

        List<ResponseBeanWrapper> responseBeanWrappers = new ArrayList<>();
        // 默认的json格式返回值处理，可在前面添加多个
        responseBeanWrappers.add(new DefaultResponseBeanWrapper());

        jsonMethodReturnValueHandler.setResponseBeanWrappers(responseBeanWrappers);

        return jsonMethodReturnValueHandler;
    }


    @Bean
    public GlobalHandlerExceptionResolver globalHandlerExceptionResolver() {
        return new GlobalHandlerExceptionResolver();
    }


}
