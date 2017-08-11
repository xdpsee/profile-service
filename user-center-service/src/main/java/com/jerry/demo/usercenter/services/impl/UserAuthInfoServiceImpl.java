package com.jerry.demo.usercenter.services.impl;

import com.jerry.demo.usercenter.api.dto.UserAuthInfo;
import com.jerry.demo.usercenter.api.enums.AuthType;
import com.jerry.demo.usercenter.api.services.UserAuthInfoService;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserAuthInfoServiceImpl implements UserAuthInfoService {

    @Override
    public UserAuthInfo getUserAuthInfo(AuthType type, String identifier) {
        return null;
    }


    @Override
    public boolean bindUser(AuthType type, String identifier, long userId) {
        return false;
    }
}
