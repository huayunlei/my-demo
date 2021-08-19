package com.my.demo.designmode.strategy.impl;

import com.my.demo.designmode.strategy.AbstractSmsProcessor;
import com.my.demo.designmode.strategy.SmsParamDTO;
import com.my.demo.designmode.strategy.SmsProcessorRegister;
import com.my.demo.designmode.strategy.SpEnum;
import org.springframework.stereotype.Service;

/**
 * @author hyl
 * @create 2020-05-21
 * @version:
 */
@Service
public class TriotechSmsProcessorImpl extends AbstractSmsProcessor {
    @Override
    public void process(SmsParamDTO<?> smsParamDTO) {
        System.out.println("triotect..." + smsParamDTO.getSpCode());
    }

    @Override
    protected SmsProcessorRegister.SmsKeyPair getKayPair() {
        return new SmsProcessorRegister.SmsKeyPair(SpEnum.TRIOTECH.getCode());
    }
}
