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
@Order(0)
@Component
public class GenderUserFilter implements UserFilter {
    @Override
    public List<User> filter(List<User> origin, User factor) {
        if (CollectionUtils.isEmpty(origin)) {
            return new ArrayList<>();
        }
        return origin.stream().filter(user -> factor.getGender() == user.getGender()).collect(Collectors.toList());
    }

    @Override
    public boolean match(User factor) {
        return factor.getGender() == 1 || factor.getGender() == 2;
    }
}
