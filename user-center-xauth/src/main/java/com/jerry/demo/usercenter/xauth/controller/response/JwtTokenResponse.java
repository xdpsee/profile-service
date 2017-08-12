package com.jerry.demo.usercenter.xauth.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenResponse {

    private int error;

    private String message;

    private String token;

}
