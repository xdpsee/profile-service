package com.jerry.demo.usercenter.data.mapper;

import com.jerry.demo.usercenter.data.po.UserPO;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int insert(UserPO user);

    int update(@Param("userId") long userId
            , @Param("nickname") String nickname
            , @Param("avatar") String avatar);

    UserPO selectById(@Param("userId") long userId);

    UserPO selectByNickname(@Param("nickname") String nickname);

}
