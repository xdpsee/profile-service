package com.jerry.demo.usercenter.xauth.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameNotFoundException extends AuthenticationException {

    public UsernameNotFoundException() {
        super("帐号不存在");
    }

}