package com.jerry.demo.usercenter.api.services;

import com.jerry.demo.usercenter.api.dto.User;

public interface UserInfoService {

    User getUser(long userId);

    User getUser(String nickname);

    boolean updateUser(User user);
}

