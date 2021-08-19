package com.my.oauth2.service;

import com.my.oauth2.domain.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hyl
 * @create 2020-08-25
 * @version: branch_member_20200601_v3_8
 */
@Service
public class UserService implements UserDetailsService {

    private List<MyUserDetails> myUserDetailsList;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        String password = passwordEncoder.encode("123456");
        myUserDetailsList = new ArrayList<>();
        myUserDetailsList.add(new MyUserDetails("macro", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
        myUserDetailsList.add(new MyUserDetails("andy", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
        myUserDetailsList.add(new MyUserDetails("mark", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<MyUserDetails> findUserList = myUserDetailsList.stream().filter(myUserDetails -> myUserDetails.getUsername().equals(username)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(findUserList)) {
            return findUserList.get(0);
        } else {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
    }

}
