package com.jerry.demo.usercenter.xauth;

import com.jerry.demo.usercenter.api.dto.UserAuth;
import com.jerry.demo.usercenter.api.enums.AuthType;
import com.jerry.demo.usercenter.api.services.UserAuthService;
import com.jerry.demo.usercenter.security.jwt.UserAuthenticationToken;
import com.jerry.demo.usercenter.xauth.jwt.exception.EmailNotFoundException;
import com.jerry.demo.usercenter.xauth.jwt.exception.MobileNotFoundException;
import com.jerry.demo.usercenter.xauth.jwt.exception.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserAuthService userAuthService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        UserAuthenticationToken token = (UserAuthenticationToken) auth;

        final UserAuth userAuth = userAuthService.getUserAuth(token.getType(), token.getIdentifier());
        if (userAuth == null) {
            if (AuthType.EMAIL.equals(token.getType())) {
                throw new EmailNotFoundException();
            } else if (AuthType.MOBILE.equals(token.getType())) {
                throw new MobileNotFoundException();
            } else if (AuthType.USERNAME.equals(token.getType())) {
                throw new UsernameNotFoundException();
            }
        }

        if (!passwordEncoder.matches((String)token.getCredentials(), userAuth.getCredential())) {
            throw new BadCredentialsException("密码错误");
        }

        token.setUserId(userAuth.getUserId());

        return token;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UserAuthenticationToken.class);
    }
}


