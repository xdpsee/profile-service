package com.jerry.demo.usercenter.xauth.security;

import com.jerry.demo.usercenter.api.dto.UserAuthInfo;
import com.jerry.demo.usercenter.api.enums.AuthType;
import com.jerry.demo.usercenter.api.services.UserAuthInfoService;
import com.jerry.demo.usercenter.security.jwt.UserAuthenticationToken;
import com.jerry.demo.usercenter.xauth.exception.EmailNotFoundException;
import com.jerry.demo.usercenter.xauth.exception.MobileNotFoundException;
import com.jerry.demo.usercenter.xauth.exception.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserAuthInfoService userAuthInfoService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        UserAuthenticationToken token = (UserAuthenticationToken) auth;

        final UserAuthInfo userAuthInfo = userAuthInfoService.getAuthInfo(token.getType(), token.getIdentifier());
        if (userAuthInfo == null) {
            if (AuthType.EMAIL.equals(token.getType())) {
                throw new EmailNotFoundException();
            } else if (AuthType.MOBILE.equals(token.getType())) {
                throw new MobileNotFoundException();
            } else if (AuthType.USERNAME.equals(token.getType())) {
                throw new UsernameNotFoundException();
            }
        }

        if (!userAuthInfoService.matchCredential(token.getType(), token.getIdentifier(), (String)token.getCredentials())) {
            throw new BadCredentialsException("密码错误");
        }

        token.setUserId(userAuthInfo.getUserId());

        return token;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UserAuthenticationToken.class);
    }
}


