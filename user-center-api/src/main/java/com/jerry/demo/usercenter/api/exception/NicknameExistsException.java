package com.jerry.demo.usercenter.api.exception;

public class NicknameExistsException extends Exception {

    public NicknameExistsException() {

        super("昵称已被使用");

    }


}
