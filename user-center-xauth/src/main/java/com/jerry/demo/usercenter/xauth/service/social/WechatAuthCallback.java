package com.jerry.demo.usercenter.xauth.service.social;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/xauth/weixin")
public class WechatAuthCallback {

    @RequestMapping("/callback")
    public void callback() {


    }

}
