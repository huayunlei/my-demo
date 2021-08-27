package com.my.demo.controller;

import com.my.demo.conditional.ListService;
import com.my.demo.event.DemoPublisher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hyl
 * @create 2020-05-23
 * @version:
 */
@Api(value = "响应自定义",tags = "test-tag")
@RestController
@RequestMapping("/test")
public class MyTestController {

    @Autowired
    private DemoPublisher demoPublisher;
    @Autowired
    private ListService listService;

    @ApiResponses({@ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @PostMapping("/knife4j")
    public String knife4jTest(String name) {

        System.out.println(name);

        return name;
    }

    @ApiResponses({@ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @PostMapping("/publishTest")
    public String publishTest(String msg) {

        System.out.println(msg);
        demoPublisher.publish(msg);

        return msg;
    }

    @ApiResponses({@ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @PostMapping("/conditionTest")
    public String conditionTest() {
        String cmd = listService.showListCmd();
        System.out.println(cmd);
        return "ok";
    }
}
