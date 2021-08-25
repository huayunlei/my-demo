package com.my.demo.bean.ioc.user;

import com.my.demo.domain.User;
import com.my.demo.util.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version:
 */
@Order(1)
@Component
public class UserNameFilter implements UserFilter {

    @Override
    public List<User> filter(List<User> origin, User factor) {
        if (CollectionUtils.isEmpty(origin)) {
            return new ArrayList<>();
        }
        return origin.stream().filter(user -> user.getName().startsWith(factor.getName())).collect(Collectors.toList());
    }

    @Override
    public boolean match(User factor) {
        return !StringUtil.isEmpty(factor.getName());
    }
}
