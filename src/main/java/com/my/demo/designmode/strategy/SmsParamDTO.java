package com.my.demo.designmode.strategy;

import lombok.Data;

/**
 * @author hyl
 * @create 2020-05-21
 * @version:
 */
@Data
public class SmsParamDTO<T> {

    private String spCode;

    private T param;

}
