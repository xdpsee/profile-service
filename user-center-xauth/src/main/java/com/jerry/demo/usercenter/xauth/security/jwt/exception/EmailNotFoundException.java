package com.jerry.demo.usercenter.xauth.security.jwt.exception;

import org.springframework.security.core.AuthenticationException;

public class EmailNotFoundException extends AuthenticationException {

    public EmailNotFoundException() {
        super("电子邮箱未注册");
    }
}
