package com.jerry.demo.usercenter.security.jwt;

import org.springframework.security.core.AuthenticationException;

public class AuthTokenExpiresException extends AuthenticationException{

    public AuthTokenExpiresException(String msg) {
        super(msg);
    }
}
