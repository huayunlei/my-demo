package com.my.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hyl
 * @create 2020-08-27
 * @version: branch_member_20200601_v3_8
 */
@RestController
@RequestMapping("/ratelimit")
public class RateLimiterGatewayController {

    @GetMapping("/test")
    public Object test() {
        Map<String,Object> result = new HashMap<>();
        result.put("data",null);
        result.put("message","ratelimit test!");
        result.put("code",200);
        return result;
    }

}
