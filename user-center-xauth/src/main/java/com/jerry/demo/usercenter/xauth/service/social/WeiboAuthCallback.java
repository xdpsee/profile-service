package com.jerry.demo.usercenter.xauth.service.social;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/xauth/weibo")
public class WeiboAuthCallback {

    @RequestMapping("/callback")
    public void callback() {



    }

}
