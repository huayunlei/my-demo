package com.my.demo.designmode.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hyl
 * @create 2020-05-18
 * @version:
 */
public class ApplicationFilterConfig {

    public List<Filter> filterList = new ArrayList<Filter>();

    public void addFilter(Filter filter){
        filterList.add(filter);
    }

    public List<Filter> getFilters(){
        return filterList;
    }

}
