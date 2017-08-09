package com.jerry.demo.usercenter.api.services;

import com.jerry.demo.usercenter.api.dto.User;

import java.util.Collection;

public interface UserService {

    User createUser(String identifier, String credential, String nickname, Collection<String> authorities);

    User getUserById(long userId);

    User getUserByNickName(String nickname);

    boolean updateAvatar(Long userId, String avatar);
}



