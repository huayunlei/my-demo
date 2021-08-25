package com.my.demo.bean.copy.orika;

/**
 * @version:
 */
public class ResourceNotExistException extends RuntimeException {

    private String msg;

    public ResourceNotExistException (String msg) {
        this.msg = msg;
    }
}
