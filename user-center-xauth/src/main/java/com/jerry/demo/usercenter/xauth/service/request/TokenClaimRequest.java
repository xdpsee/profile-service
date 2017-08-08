package com.jerry.demo.usercenter.xauth.service.request;

import lombok.Data;

@Data
public class TokenClaimRequest {

    private String principal;

    private String credential;

}
