package com.jerry.demo.usercenter.services.impl;

import com.jerry.demo.usercenter.api.dto.User;
import com.jerry.demo.usercenter.api.services.UserService;
import com.jerry.demo.usercenter.data.mapper.UserMapper;
import com.jerry.demo.usercenter.data.po.UserPO;
import com.jerry.demo.usercenter.services.utils.UserCacheNames;
import com.jerry.demo.usercenter.services.utils.aop.annotations.UserCacheEvict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User createUser(String identifier, String credential, String nickname) {
        return null;
    }

    @Override
    @Cacheable(value = UserCacheNames.userCacheById)
    public User getUserById(long userId) {
        UserPO po = userMapper.selectById(userId);
        if (po != null) {
            return new User(po.getId(),po.getGmtCreate().getTime(), po.getNickname(), po.getAvatar());
        }

        return null;
    }

    @Override
    @Cacheable(value = UserCacheNames.userCacheByNickname)
    public User getUserByName(String nickname) {
        UserPO po = userMapper.selectByNickname(nickname);
        if (po != null) {
            return new User(po.getId(),po.getGmtCreate().getTime(), po.getNickname(), po.getAvatar());
        }

        return null;
    }

    @Override
    @UserCacheEvict(userCacheNames = {UserCacheNames.userCacheById, UserCacheNames.userCacheByNickname}, key="#p0")
    public boolean updateUser(User user) {
        final int rows = userMapper.update(user.getId(), user.getNickname(), user.getAvatar());
        return rows > 0;
    }
}


