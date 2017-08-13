package com.jerry.demo.usercenter.xauth.controller;

import com.jerry.demo.usercenter.xauth.controller.request.TokenClaimRequest;
import com.jerry.demo.usercenter.xauth.controller.request.TokenRefreshRequest;
import com.jerry.demo.usercenter.xauth.controller.response.JwtTokenResponse;
import com.jerry.demo.usercenter.xauth.exception.EmailNotFoundException;
import com.jerry.demo.usercenter.xauth.exception.MobileNotFoundException;
import com.jerry.demo.usercenter.xauth.exception.UsernameNotFoundException;
import com.jerry.demo.usercenter.xauth.security.UserAuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/xauth/token")
public class AuthorizController {

    @Autowired
    private UserAuthenticationUtils authenticationUtils;

    @ResponseBody
    @RequestMapping(value = "/claim", method = RequestMethod.POST)
    public JwtTokenResponse claimToken(@RequestBody TokenClaimRequest request) {
        try {
            String token = authenticationUtils.authenticate(request.getPrincipal(), request.getCredential());
            return new JwtTokenResponse(0, "ok", token);
        } catch (Exception e) {
            return error(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public String refreshToken(@RequestBody TokenRefreshRequest request) {
        return "";
    }

    private JwtTokenResponse error(Exception e) {
        int error;
        String message;
        if (e instanceof UsernameNotFoundException) {
            error = 1;
            message = "用户名未注册";
        } else if (e instanceof EmailNotFoundException) {
            error = 2;
            message = "电子邮箱未注册";
        } else if (e instanceof MobileNotFoundException) {
            error = 3;
            message = "手机号未注册";
        } else if (e instanceof AuthenticationException){
            error = 4;
            message = "认证错误";
        } else {
            error = 5;
            message = "未知错误";
        }

        return new JwtTokenResponse(error, message, null);
    }

}
