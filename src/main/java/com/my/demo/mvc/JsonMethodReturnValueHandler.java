package com.my.demo.mvc;

import com.my.demo.serializer.FastJsonSerializer;
import com.my.demo.serializer.TypeSerializer;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * json返回格式handler
 * @version:
 */
public class JsonMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    private TypeSerializer typeSerializer = new FastJsonSerializer();

    private List<ResponseBeanWrapper> responseBeanWrappers;

    /**
     * 检验是否支持本处理器处理，返回true会执行handleReturnValue
     * @param returnType
     * @return
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return isJsonResponse(returnType, ResponseJson.class) || isJsonResponse(returnType, ResponseBody.class);
    }

    /**
     * 处理返回值的方法，returnValue即是controller方法中的返回值
     * @param returnValue
     * @param returnType
     * @param mavContainer
     * @param webRequest
     * @throws Exception
     */
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        HttpServletResponse servletResponse = webRequest.getNativeResponse(HttpServletResponse.class);
        Object response = returnValue;
        if (response == null) {

        }

        for (ResponseBeanWrapper responseBeanWrapper : responseBeanWrappers) {
            if (responseBeanWrapper.supportsType(returnType)) {
                response = responseBeanWrapper.wrap(returnType, response);
                break;
            }
        }

        byte[] body = typeSerializer.write(response);
        servletResponse.setContentLength(body.length);
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        FileCopyUtils.copy(body, servletResponse.getOutputStream());
    }


    private boolean isJsonResponse(MethodParameter methodParameter, Class t) {
        return AnnotatedElementUtils.getMergedAnnotation(methodParameter.getMethod(), t) != null ||
                AnnotatedElementUtils.getMergedAnnotation(methodParameter.getDeclaringClass(), t) != null;
    }

    public List<ResponseBeanWrapper> getResponseBeanWrappers() {
        return responseBeanWrappers;
    }

    public void setResponseBeanWrappers(List<ResponseBeanWrapper> responseBeanWrappers) {
        this.responseBeanWrappers = responseBeanWrappers;
    }
}
