package com.jerry.demo.usercenter.data.mapper;

import com.jerry.demo.usercenter.api.enums.SocialType;
import com.jerry.demo.usercenter.data.po.UserOpenInfoPO;
import org.apache.ibatis.annotations.Param;

public interface UserOpenInfoMapper {

    int insert(UserOpenInfoPO openInfo);

    UserOpenInfoPO select(@Param("type") SocialType type, @Param("openId") long openId);

}



