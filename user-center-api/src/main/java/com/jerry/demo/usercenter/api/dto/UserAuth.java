package com.jerry.demo.usercenter.api.dto;

import com.jerry.demo.usercenter.api.enums.AuthType;
import lombok.Data;

@Data
public class UserAuth {

    private AuthType type;

    private String identifier;

    private String credential;

    private boolean verified;

    private Long userId;
}
