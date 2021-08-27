package com.my.demo.mvc;

import com.my.demo.constant.exception.ServiceException;
import com.my.demo.serializer.FastJsonSerializer;
import com.my.demo.serializer.TypeSerializer;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.my.demo.constant.exception.CodeConstant.PARAM_ERROR;
import static com.my.demo.constant.exception.CodeConstant.REQUEST_METHOD_INCOMPATIBLE;
import static com.my.demo.constant.exception.CodeConstant.UNKNOWN_ERROR;

/**
 * 全局异常处理
 * @version:
 */
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver, Ordered {

    private TypeSerializer typeSerializer = new FastJsonSerializer();

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        StandardResponse standardResponse = null;
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            standardResponse = StandardResponse.fail(serviceException.getCode(), serviceException.getMessage());
        } else if (e instanceof ConstraintViolationException) {
                ConstraintViolationException ve = (ConstraintViolationException) e;

                List<ConstraintViolation<?>> constraintViolations = new ArrayList<>(ve.getConstraintViolations());
                if (!CollectionUtils.isEmpty(constraintViolations)) {
                    standardResponse = StandardResponse.fail(PARAM_ERROR.getCode(), constraintViolations.get(0).getMessage());
                } else {
                    standardResponse = StandardResponse.fail(UNKNOWN_ERROR.getCode(), UNKNOWN_ERROR.getMsg());
                }
        } else if (e instanceof MissingServletRequestParameterException) {
            standardResponse = StandardResponse.fail(PARAM_ERROR.getCode(), PARAM_ERROR.getMsg());
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            standardResponse = StandardResponse.fail(REQUEST_METHOD_INCOMPATIBLE.getCode(), REQUEST_METHOD_INCOMPATIBLE.getMsg());
        } else if (e instanceof IllegalArgumentException) {
            standardResponse = StandardResponse.fail(PARAM_ERROR.getCode(), e.getMessage());
        }

        byte[] body = typeSerializer.write(standardResponse);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding("utf-8");
        response.setContentLength(body.length);

        try {
            FileCopyUtils.copy(body, response.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new ModelAndView();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
