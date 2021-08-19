package com.my.demo.designmode.factory;

/**
 * @author hyl
 * @create 2020-05-13
 * @version:
 */
public interface BusinessService extends TypeSupport<BusinessEnum> {

    AbstractBusinessBean getBusinessBean(Long param);
}
