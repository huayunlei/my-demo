package com.my.demo.designmode.strategy;

/**
 * @author hyl
 * @create 2020-05-21
 * @version:
 */
public interface SmsProcessor {

    void process(SmsParamDTO<?> smsParamDTO);
}
