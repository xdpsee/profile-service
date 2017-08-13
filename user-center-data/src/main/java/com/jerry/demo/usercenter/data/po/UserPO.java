package com.jerry.demo.usercenter.data.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPO extends BasePO {

    private String nickname;

    private String avatar;

    private List<String> authorities = new ArrayList<>();
}

