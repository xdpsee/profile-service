package com.jerry.demo.usercenter.api.exception;

public class AccountExistsException extends Exception {

    public AccountExistsException() {
        super("帐户已存在");
    }
}
