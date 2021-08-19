package com.my.demo.designmode.strategy;

import lombok.Data;

/**
 * @author hyl
 * @create 2020-05-21
 * @version:
 */
public enum SpEnum {
    EMAY("emay"),
    MANDAO("mandao"),
    TRIOTECH("triotech"),

    ;

    private String code;

    public String getCode() {
        return code;
    }

    SpEnum(String code) {
        this.code = code;
    }

    public static void main(String[] args) {
        System.out.println(SpEnum.EMAY.getCode());
    }
}
