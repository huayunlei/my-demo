package com.my.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author hyl
 * @create 2020-07-30
 * @version: branch_member_20200601_v3_7
 */
public class DemoEvent extends ApplicationEvent {

    private String msg;

    public DemoEvent(Object source) {
        super(source);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
