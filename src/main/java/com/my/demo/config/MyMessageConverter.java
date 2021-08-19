package com.my.demo.config;

import com.my.demo.domain.User;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author hyl
 * @create 2020-08-15
 * @version: branch_member_20200601_v3_7_1
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<User> {

    public MyMessageConverter() {
        super(new MediaType("application", "x-hyl", Charset.forName("UTF-8")));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    protected User readInternal(Class<? extends User> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        String[] arr = temp.split("-");
        return new User(arr[0], new Integer(arr[1]), true, new Integer(arr[2]));
    }

    @Override
    protected void writeInternal(User user, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "hello " + user.getName() + "-" + user.getAge() + "-" + user.isMan();
        httpOutputMessage.getBody().write(out.getBytes());
    }
}
