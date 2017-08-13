package com.jerry.demo.usercenter.data.mapper;

import com.jerry.demo.usercenter.data.po.UserPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int insert(UserPO user);

    int update(@Param("userId") long userId
            , @Param("nickname") String nickname
            , @Param("avatar") String avatar);

    int updateAuthorities(@Param("userId") long userId
            , @Param("authorities")List<String> authorities);

    UserPO selectById(@Param("userId") long userId);

    UserPO selectByNickname(@Param("nickname") String nickname);

}
