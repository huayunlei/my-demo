package com.my.demo;

import com.my.demo.designmode.chain.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author hyl
 * @create 2020-05-18
 * @version:
 */
public class ChainTest {

    @Test
    public void test() {
        //web容器首先new出一个ApplicationFilterConfig对象，并读取本应用程序中所有配置的过滤器，将其存放在ApplicationFilterConfig中
        ApplicationFilterConfig  applicationFilterConfig = new ApplicationFilterConfig();

        //读取本应用程序中所有配置的过滤器
        Filter hTMLFilter = new HTMLFilter();
        Filter cSSFilter = new CSSFilter();

        //将过滤器存放在ApplicationFilterConfig中
        applicationFilterConfig.addFilter(cSSFilter);
        applicationFilterConfig.addFilter(hTMLFilter);

        //web容器创建RequestInface对象，ResponseInface对象，FilterChain对象
        RequestInface request = new MyRequest();
        ResponseInface response = new MyResponse();
        FilterChain filterChain = new MyFilterChain(applicationFilterConfig);

        //我们从客户端接收到的信息
        String message = "我们正在测试责任链设计模式";
        request.setRequestStr(message);

        filterChain.doFilter(request, response);
        System.out.println(response.getResponseStr());

    }
}
