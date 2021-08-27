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
public class ArgumentResolverReturnValueRegister implements InitializingBean {

    private RequestMappingHandlerAdapter adapter;

    private List<HandlerMethodReturnValueHandler> returnValueHandlers;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList(adapter.getReturnValueHandlers());
        this.decorateReturnValueHandlers(handlers);
        adapter.setReturnValueHandlers(handlers);
    }


    private void decorateReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        int returnValueHandlerIndex = -1;
        for (int i = 0; i < handlers.size(); i++) {
            if (handlers.get(i) instanceof RequestResponseBodyMethodProcessor) {
                returnValueHandlerIndex = i;
                break;
            }
        }

        for (HandlerMethodReturnValueHandler returnValueHandler : returnValueHandlers) {
            handlers.add(returnValueHandlerIndex, returnValueHandler);
        }
    }

    public RequestMappingHandlerAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(RequestMappingHandlerAdapter adapter) {
        this.adapter = adapter;
    }

    public List<HandlerMethodReturnValueHandler> getReturnValueHandlers() {
        return returnValueHandlers;
    }

    public void setReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        this.returnValueHandlers = returnValueHandlers;
    }
}
