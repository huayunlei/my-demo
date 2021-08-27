package com.my.demo.constant;

/**
 * @version:
 */
public enum CodeConstant {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),

    /**
     * 成功
     */
    FAIL(10000, "失败");

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
