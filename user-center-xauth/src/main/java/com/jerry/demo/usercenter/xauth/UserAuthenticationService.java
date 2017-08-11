package com.jerry.demo.usercenter.xauth;

import com.jerry.demo.usercenter.api.enums.AuthType;
import com.jerry.demo.usercenter.api.services.UserAuthInfoService;
import com.jerry.demo.usercenter.security.jwt.Principal;
import com.jerry.demo.usercenter.security.jwt.UserAuthenticationToken;
import com.jerry.demo.usercenter.security.jwt.UserTokenUtils;
import com.jerry.demo.usercenter.xauth.jwt.cache.JwtTokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

@Component
public class UserAuthenticationService {

    private static final String EMAIL_PATTERN = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";

    private static final String MOBILE_PATTERN = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\\\d{8}$";

    @Autowired
    private AuthenticationManager authenticationManager;

    public String authenticate(final String identifier, final String credential) {

        AuthType authType = AuthType.USERNAME;
        if (identifier.matches(EMAIL_PATTERN)) {
            authType = AuthType.EMAIL;
        } else if (identifier.matches(MOBILE_PATTERN)) {
            authType = AuthType.MOBILE;
        }

        UserAuthenticationToken upToken = new UserAuthenticationToken(authType, identifier, credential, new ArrayList<>());
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);



        return UserTokenUtils.generateToken(new Principal(authType, identifier));
    }
}






