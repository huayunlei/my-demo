package com.my.demo.bean.ioc.user;

import com.my.demo.bean.ioc.BeanMatcher;
import com.my.demo.domain.User;

import java.util.List;

/**
 * @version:
 */
public interface UserFilter extends BeanMatcher<User> {

    List<User> filter(List<User> origin, User factor);
}
