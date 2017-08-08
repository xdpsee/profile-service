package com.jerry.demo.usercenter.xauth.security;

import com.jerry.demo.usercenter.api.enums.AuthType;
import com.jerry.demo.usercenter.xauth.security.jwt.JwtAuthenticationToken;
import com.jerry.demo.usercenter.xauth.security.jwt.JwtTokenUtils;
import com.jerry.demo.usercenter.xauth.security.jwt.cache.JwtTokenCache;
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
    @Autowired
    private JwtTokenCache jwtTokenCache;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    public String authenticate(final String identifier, final String credential) {

        AuthType authType = AuthType.USERNAME;
        if (identifier.matches(EMAIL_PATTERN)) {
            authType = AuthType.EMAIL;
        } else if (identifier.matches(MOBILE_PATTERN)) {
            authType = AuthType.MOBILE;
        }
        JwtAuthenticationToken upToken = new JwtAuthenticationToken(authType, identifier, credential, new ArrayList<>());
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String principal = upToken.getPrincipal().toString();
        String token = jwtTokenCache.get(upToken.getPrincipal().toString());
        if (!StringUtils.isEmpty(token)) {
            return token;
        }

        token = jwtTokenUtils.generateToken(principal);
        if (token != null) {
            jwtTokenCache.put(principal, token, jwtTokenUtils.getExpiresDateFromToken(token));
        }

        return token;
    }
}

