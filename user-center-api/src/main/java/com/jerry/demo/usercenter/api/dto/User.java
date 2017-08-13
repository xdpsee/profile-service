package com.jerry.demo.usercenter.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private Long gmtCreate;

    private String nickname;

    private String avatar;

    private List<String> authorities = new ArrayList<>();

}

