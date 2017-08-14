package com.jerry.demo.usercenter.xauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/xauth/weibo")
public class WeiboAuthController {

    @RequestMapping("/login")
    public String login() {

        return "";

    }

    @RequestMapping("/callback")
    public void callback() {



    }

}
