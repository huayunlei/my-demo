package com.my.demo.designmode.proxy;

import com.my.demo.designmode.proxy.annotation.RestApi;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hyl
 * @create 2021-04-21
 * @version: branch_member_20210407_v4_3_2
 */
@RestApi(value = "http://127.0.0.1:8080/shool")
public interface IShoolApi {

    @GetMapping(value = "/listShool")
    String listShool();

}
