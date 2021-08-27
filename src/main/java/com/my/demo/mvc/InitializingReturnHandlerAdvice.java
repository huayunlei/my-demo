package com.my.demo.mvc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 初始化 HandlerMethodReturnValueHandler 返回值处理器
 * 可以处理自定义返回值，不局限于@ResponseBody注释的
 * @version:
 */
@Configuration
public class InitializingReturnHandlerAdvice implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList(returnValueHandlers);
        this.decorateHandlers(handlers);
        adapter.setReturnValueHandlers(handlers);
    }


    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        for (HandlerMethodReturnValueHandler handler : handlers) {
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                JsonMethodReturnValueHandler decorator = new JsonMethodReturnValueHandler(
                        (RequestResponseBodyMethodProcessor) handler);

                List<ResponseBeanWrapper> responseBeanWrappers = Arrays.asList(new DefaultResponseBeanWrapper());
                decorator.setResponseBeanWrappers(responseBeanWrappers);

                int index = handlers.indexOf(handler);
                handlers.set(index, decorator);
                break;
            }
        }
    }
}
