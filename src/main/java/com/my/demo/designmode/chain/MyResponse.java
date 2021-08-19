package com.my.demo.designmode.chain;

/**
 * @author hyl
 * @create 2020-05-18
 * @version:
 */
public class MyResponse implements ResponseInface {

    public String responseStr;

    @Override
    public String getResponseStr() {
        return responseStr;
    }

    @Override
    public void setResponseStr(String responseStr) {
        this.responseStr = responseStr;
    }

}
