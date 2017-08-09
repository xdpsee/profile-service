package com.jerry.demo.usercenter.api.services;

import com.jerry.demo.usercenter.api.dto.UserAuthInfo;
import com.jerry.demo.usercenter.api.enums.AuthType;

public interface UserAuthInfoService {

    UserAuthInfo getUserAuthInfo(AuthType type, String identifier);

    UserAuthInfo getUserAuthInfo(String jwtToken);

    String genJwtToken(AuthType type, String identifier);

    boolean bindUser(AuthType type, String identifier, long userId);

}
