package com.my.demo.bean.ioc.user;

import com.my.demo.domain.User;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version:
 */
@Order(2)
@Component
public class UserAgeFilter implements UserFilter {

    @Override
    public List<User> filter(List<User> origin, User factor) {
        if (CollectionUtils.isEmpty(origin)) {
            return new ArrayList<>();
        }
        return origin.stream().filter(user -> factor.getAge() == user.getAge()).collect(Collectors.toList());
    }

    @Override
    public boolean match(User factor) {
        return factor.getAge() > 0 && factor.getAge() < 130;
    }
}
