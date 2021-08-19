package com.my.demo.designmode.chain;

/**
 * @author hyl
 * @create 2020-05-18
 * @version:
 */
public class MyRequest implements RequestInface {

    private String requestStr;

    @Override
    public String getRequestStr() {
        return requestStr;
    }

    @Override
    public void setRequestStr(String requestStr) {
        this.requestStr = requestStr;
    }

}
