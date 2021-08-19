package com.my.demo;

import com.my.demo.designmode.factory.AbstractBusinessBean;
import com.my.demo.designmode.factory.BusinessEnum;
import com.my.demo.designmode.factory.BusinessFactory;
import com.my.demo.designmode.factory.BusinessService;
import com.my.demo.designmode.proxy.IStudentApi;
import com.my.demo.designmode.proxy2.MyInterceptor;
import com.my.demo.designmode.proxy2.MyInterceptorImpl;
import com.my.demo.designmode.proxy2.MyTarget;
import com.my.demo.designmode.proxy2.MyTargetImpl;
import com.my.demo.designmode.strategy.SmsParamDTO;
import com.my.demo.designmode.strategy.SmsProcessorRegister;
import com.my.demo.designmode.strategy.SpEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MyDemoApplicationTests {

    @Test
    void contextLoads() {

        System.out.println(1);
    }

    @Autowired
    private BusinessFactory businessFactory;

    @Test
    void factoryMode() {
        BusinessService businessService = businessFactory.get(BusinessEnum.A);
        AbstractBusinessBean bean = businessService.getBusinessBean(8L);
    }

    @Autowired
    private IStudentApi iStudentApi;

    @Test
    public void test01() {
        String objectFlux = iStudentApi.listStudent();
        System.out.println("Hello454");
        System.out.println(objectFlux);
//        objectFlux.toStream().forEach(System.out::println);

    }

    @Test
    public void testProxy() {
        MyInterceptor myInterceptor = new MyInterceptorImpl();

        MyTarget target = (MyTarget) myInterceptor.register(new MyTargetImpl());
        target.execute1();

        target.execute1();
    }

    @Autowired
    private SmsProcessorRegister smsProcessorRegister;

    @Test
    public void testStrategy() {
        SmsParamDTO smsParamDTO = new SmsParamDTO();
        smsParamDTO.setSpCode(SpEnum.TRIOTECH.getCode());
        smsProcessorRegister.get(new SmsProcessorRegister.SmsKeyPair(smsParamDTO.getSpCode())).process(smsParamDTO);
    }

}
