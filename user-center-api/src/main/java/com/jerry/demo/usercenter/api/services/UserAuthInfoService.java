package com.jerry.demo.usercenter.api.services;

import com.jerry.demo.usercenter.api.dto.UserAuthInfo;
import com.jerry.demo.usercenter.api.enums.AuthType;

public interface UserAuthInfoService {

    UserAuthInfo getUserAuthInfo(AuthType type, String identifier);

    boolean updateCredential(AuthType authType
            , String identifier
            , String credential);

    boolean bindUser(AuthType type, String identifier, long userId);

}


