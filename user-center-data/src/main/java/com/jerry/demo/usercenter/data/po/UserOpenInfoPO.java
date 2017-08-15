package com.jerry.demo.usercenter.data.po;

import com.jerry.demo.usercenter.api.enums.SocialType;
import lombok.Data;

@Data
public class UserOpenInfoPO extends BasePO {

    private SocialType type;

    private String identifier;

    private Long openId;

    private String nickname;

    private String avatar;

}
