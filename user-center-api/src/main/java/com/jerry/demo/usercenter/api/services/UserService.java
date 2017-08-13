package com.jerry.demo.usercenter.api.services;

import com.jerry.demo.usercenter.api.dto.User;
import com.jerry.demo.usercenter.api.enums.AuthType;
import com.jerry.demo.usercenter.api.exception.AccountExistsException;
import com.jerry.demo.usercenter.api.exception.NicknameExistsException;

import java.util.List;

public interface UserService {

    User createUser(String nickname
            , List<String> authorities
            , AuthType authType
            , String identifier
            , String credential) throws NicknameExistsException,AccountExistsException;

    User getUserById(long userId);

    User getUserByNickName(String nickname);

    boolean updateAvatar(Long userId, String avatar);

    boolean updateAuthorities(Long userId, List<String> authorities);
}



