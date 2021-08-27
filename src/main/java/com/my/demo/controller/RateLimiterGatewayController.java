package com.my.demo.controller;

import com.my.demo.mvc.ResponseJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hyl
 * @create 2020-08-27
 */
@Controller
@ResponseJson
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
