package com.my.demo.designmode.chain;

import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author hyl
 * @create 2020-05-18
 * @version:
 */
public class MyFilterChain implements FilterChain {
    private int index = 0;

    private ApplicationFilterConfig applicationFilterConfig;


    @Override
    public void doFilter(RequestInface request, ResponseInface response) {
        List<Filter> filters = applicationFilterConfig.getFilters();
        if (index == filters.size()){
            return;
        }else{
            if (!StringUtils.isEmpty(response.getResponseStr())) {
                return;
            }
            Filter f = filters.get(index);
            index++;
            f.doFilter(request, response, this);
        }
    }

    public MyFilterChain(ApplicationFilterConfig applicationFilterConfig) {
        super();
        this.applicationFilterConfig = applicationFilterConfig;
    }

}
