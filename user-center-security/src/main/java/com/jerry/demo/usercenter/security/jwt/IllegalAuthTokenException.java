package com.jerry.demo.usercenter.security.jwt;

import org.springframework.security.core.AuthenticationException;

public class IllegalAuthTokenException extends AuthenticationException {

    public IllegalAuthTokenException(String msg) {
        super(msg);
    }

}
