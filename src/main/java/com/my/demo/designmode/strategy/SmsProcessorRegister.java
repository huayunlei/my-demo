package com.my.demo.designmode.strategy;

import com.google.common.collect.Maps;
import com.my.demo.SpringContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author hyl
 * @create 2020-05-21
 * @version:
 */
@Component
public class SmsProcessorRegister {

    private Map<SmsKeyPair,String> config = Maps.newHashMap();

    public void put(SmsKeyPair key, String beanName) {
        config.put(key, beanName);
    }

    public SmsProcessor get(SmsKeyPair key) {
        return SpringContextHolder.getBean(config.get(key));
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SmsKeyPair {
        private String spCode;
    }

}
