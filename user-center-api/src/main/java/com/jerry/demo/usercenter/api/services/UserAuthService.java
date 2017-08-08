package com.jerry.demo.usercenter.api.services;

import com.jerry.demo.usercenter.api.dto.UserAuth;
import com.jerry.demo.usercenter.api.enums.AuthType;

public interface UserAuthService {

    UserAuth getUserAuth(AuthType type, String identifier);

    boolean bindUser(AuthType type, String identifier, long userId);

}
