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
    public UserAuthInfo getUserAuthInfo(String jwtToken) {
        return null;
    }

    @Override
    public String genJwtToken(AuthType type, String identifier) {

//        final String principal = upToken.getPrincipal().toString();
//        String token = jwtTokenCache.get(upToken.getPrincipal().toString());
//        if (!StringUtils.isEmpty(token)) {
//            return token;
//        }
//
//        token = UserTokenUtils.generateToken(principal);
//        if (token != null) {
//            jwtTokenCache.put(principal, token, UserTokenUtils.getExpiresDateFromToken(token));
//        }

        return null;
    }

    @Override
    public boolean bindUser(AuthType type, String identifier, long userId) {
        return false;
    }
}
