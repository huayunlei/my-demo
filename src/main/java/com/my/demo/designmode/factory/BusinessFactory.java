package com.my.demo.designmode.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hyl
 * @create 2020-05-13
 * @version:
 */
@Service
public class BusinessFactory extends AbstractCommFacadeFactory<BusinessService, BusinessEnum> {

    @Autowired
    private List<BusinessService> services;

    @Override
    public List<BusinessService> getServices() {
        return services;
    }
}
