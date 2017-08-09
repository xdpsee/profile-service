package com.jerry.demo.usercenter.xauth.service;

import com.jerry.demo.usercenter.xauth.UserAuthenticationService;
import com.jerry.demo.usercenter.xauth.service.request.TokenClaimRequest;
import com.jerry.demo.usercenter.xauth.service.request.TokenRefreshRequest;
import com.jerry.demo.usercenter.xauth.service.response.JwtTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/xauth/token")
public class AuthorizController {

    @Autowired
    private UserAuthenticationService authenticationService;

    @ResponseBody
    @RequestMapping("/claim")
    public JwtTokenResponse claimToken(@RequestBody TokenClaimRequest request) {

        String token = authenticationService.authenticate(request.getPrincipal(), request.getCredential());

        return new JwtTokenResponse();
    }

    @ResponseBody
    @RequestMapping("/refresh")
    public String refreshToken(@RequestBody TokenRefreshRequest request) {
        return "";
    }

}
