package com.my.demo.designmode.factory;

import org.springframework.stereotype.Service;

/**
 * @author hyl
 * @create 2020-05-13
 * @version:
 */
@Service
public class BusinessServiceImplB implements BusinessService {

    @Override
    public BusinessEnum getType() {
        return BusinessEnum.B;
    }

    @Override
    public AbstractBusinessBean getBusinessBean(Long param) {
        return new BusinessBeanB();
    }
}
