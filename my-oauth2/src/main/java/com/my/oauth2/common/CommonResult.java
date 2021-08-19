package com.my.oauth2.common;

/**
 * @author hyl
 * @create 2020-08-26
 * @version: branch_member_20200601_v3_8
 */
public class CommonResult {
    public static Object forbidden(String message) {
        return message;
    }

    public static Object unauthorized(String message) {
        return message;
    }
}
