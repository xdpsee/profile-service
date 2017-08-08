package com.jerry.demo.usercenter.api.services;

import com.jerry.demo.usercenter.api.dto.User;

public interface UserService {

    User createUser(String identifier, String credential, String nickname);

    User getUserById(long userId);

    User getUserByName(String nickname);

    boolean updateUser(User user);
}


