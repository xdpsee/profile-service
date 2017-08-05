package com.jerry.demo.usercenter.data.po;

import lombok.Data;

import java.util.Date;

@Data
public class UserPO {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String nickname;

    private String avatar;
}

