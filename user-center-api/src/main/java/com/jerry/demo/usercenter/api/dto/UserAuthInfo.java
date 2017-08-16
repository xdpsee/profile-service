package com.jerry.demo.usercenter.api.dto;

import com.jerry.demo.usercenter.api.enums.AuthType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserAuthInfo {

    private AuthType type;

    private String identifier;

    private boolean verified;

    private Long userId;

    private List<String> authorities = new ArrayList<>();
}
