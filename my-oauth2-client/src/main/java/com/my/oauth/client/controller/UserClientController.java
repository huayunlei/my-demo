package com.my.oauth.client.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyl
 * @create 2020-08-25
 * @version: branch_member_20200601_v3_8
 */
@RestController
@RequestMapping("/user")
public class UserClientController {

    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/testAuth")
    public Object testAuth(String msg) {
        System.out.println(msg);
        return msg;
    }

    @PreAuthorize("hasAnyAuthority('admin','macro')")
    @GetMapping("/auth/admin")
    public Object adminAuth() {
        return "Has admin auth!";
    }

}
