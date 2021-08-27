package com.my.demo.constant.exception;

/**
 * @version:
 */
public enum CodeConstant {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),

    /**
     * 服务器内部错误（未知错误）
     */
    UNKNOWN_ERROR(10000, "服务器内部错误"),
    /**
     * 服务器内部错误（未知错误）
     */
    PARAM_VALIDATE_ERROR(11000, "参数校验失败"),

    /**
     * 请求参数错误
     */
    PARAM_ERROR(11002, "请求参数错误"),

    /**
     * 请求方法不支持
     */
    REQUEST_METHOD_INCOMPATIBLE(11003, "请求方法不支持"),

    /**
     * 失败
     */
    FAIL(20000, "失败");

    CodeConstant(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
    }

    private Integer	code;

    private String	msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getMsgByCode(Integer code) {
        for (CodeConstant errorCode : CodeConstant.values()) {
            if (code.equals(errorCode.getCode())) {
                return errorCode.getMsg();
            }
        }
        return null;
    }
}
