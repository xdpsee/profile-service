package com.jerry.demo.usercenter.xauth.controller.request;

import lombok.Data;

@Data
public class TokenClaimRequest {

    private String principal;

    private String credential;

}
