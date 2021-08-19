package com.my.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyl
 * @create 2020-08-25
 * @version: branch_member_20200601_v3_8
 */
@RestController
@RequestMapping("/noauth")
public class NoAuthController {

    @GetMapping("/testNOAuth")
    public Object testNOAuth(String msg) {
        System.out.println(msg);
        return msg;
    }

    @GetMapping("/testNOAuth2")
    public Object testNOAuth2(String msg) {
        System.out.println(msg);
        return msg;
    }

    @GetMapping("/testNOAuth3")
    public Object testNOAuth3(String msg) {
        System.out.println(msg);
        return msg;
    }

}
