package com.jerry.demo.usercenter.xauth.exception;


import org.springframework.security.core.AuthenticationException;

public class MobileNotFoundException extends AuthenticationException {

    public MobileNotFoundException() {
        super("手机号未注册");
    }

}

