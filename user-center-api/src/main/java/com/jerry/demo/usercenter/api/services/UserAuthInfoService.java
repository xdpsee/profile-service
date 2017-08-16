package com.jerry.demo.usercenter.api.services;

import com.jerry.demo.usercenter.api.dto.UserAuthInfo;
import com.jerry.demo.usercenter.api.enums.AuthType;
import com.jerry.demo.usercenter.api.exception.UserBindingException;
import com.jerry.demo.usercenter.api.exception.UserNotFoundException;

public interface UserAuthInfoService {

    UserAuthInfo getAuthInfo(AuthType type, String identifier);

    boolean bindUser(AuthType type, String identifier, long userId)
            throws UserNotFoundException, UserBindingException;

    boolean updateCredential(AuthType authType
            , String identifier
            , String credential);

    boolean matchCredential(AuthType type, String identifier, String credential);
}


