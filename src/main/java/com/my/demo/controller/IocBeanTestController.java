package com.my.demo.controller;

import com.google.common.collect.Lists;
import com.my.demo.bean.ioc.BeanList;
import com.my.demo.bean.ioc.user.UserFilter;
import com.my.demo.domain.User;
import com.my.demo.mvc.ResponseJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version:
 */
@Controller
@ResponseJson
@RequestMapping("/ioc")
public class IocBeanTestController {

    @Resource
    private BeanList<User, UserFilter> userFilters;

    @PostMapping("/getFilterUsers")
    public List<User> getFilterUsers(@RequestBody User user) {
        List<User> origin = Lists.newArrayList(
                new User(1, "test1111", 12, 1),
                new User(2, "test222", 0, 1),
                new User(3, "test333", 12, 2),
                new User(4, "pppp", 12, 1),
                new User(5, "test333", 20, 1),
                new User(6, "5555", 12, 1)
        );

        List<User> userList = origin;
        for (UserFilter filter : userFilters.list(user)) {
            userList = filter.filter(userList, user);
        }
        return userList;
    }
}
