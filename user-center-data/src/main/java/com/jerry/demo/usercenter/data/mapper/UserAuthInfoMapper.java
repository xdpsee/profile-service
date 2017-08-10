package com.jerry.demo.usercenter.data.mapper;

import com.jerry.demo.usercenter.api.enums.AuthType;
import com.jerry.demo.usercenter.data.po.UserAuthInfoPO;
import org.apache.ibatis.annotations.Param;

public interface UserAuthInfoMapper {

    int insert(UserAuthInfoPO userAuthInfo);


    UserAuthInfoPO select(@Param("type") AuthType type
            , @Param("identifier") String identifier);

}
