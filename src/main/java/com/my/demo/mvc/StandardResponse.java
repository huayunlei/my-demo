package com.my.demo.mvc;

import com.my.demo.constant.exception.CodeConstant;

import java.util.Date;

/**
 * @version:
 */
public class StandardResponse<T> {

    private int code = 0;

    private Boolean success;

    private String msg = null;

    private T data = null;

    private Long timestamp;

    public boolean successResponse() {
        if (success != null) {
            return success;
        }
        return code == CodeConstant.SUCCESS.getCode();
    }

    public static StandardResponse fail(int code, String msg) {
        StandardResponse standardResponse = new StandardResponse();
        standardResponse.setCode(code);
        standardResponse.setMsg(msg);
        standardResponse.setSuccess(false);
        standardResponse.setTimestamp(new Date().getTime());
        return standardResponse;
    }

    public static StandardResponse fail(CodeConstant codeConstant) {
        StandardResponse standardResponse = new StandardResponse();
        standardResponse.setCode(codeConstant.getCode());
        standardResponse.setMsg(codeConstant.getMsg());
        standardResponse.setSuccess(false);
        standardResponse.setTimestamp(new Date().getTime());
        return standardResponse;
    }

    public static <T> StandardResponse<T> success(T data) {
        StandardResponse<T> standardResponse = new StandardResponse<>();
        standardResponse.setMsg("成功");
        standardResponse.setSuccess(true);
        standardResponse.setCode(CodeConstant.SUCCESS.getCode());
        standardResponse.setData(data);
        standardResponse.setTimestamp(new Date().getTime());
        return standardResponse;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
