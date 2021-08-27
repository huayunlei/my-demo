package com.my.demo.constant.exception;

/**
 * @version:
 */
public class ServiceException extends RuntimeException {

    private int code;

    public ServiceException(int code) {
        this.code = code;
    }

    public ServiceException(int data, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
