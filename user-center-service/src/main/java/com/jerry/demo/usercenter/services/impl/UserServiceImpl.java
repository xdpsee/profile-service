package com.jerry.demo.usercenter.services.impl;

import com.jerry.demo.usercenter.api.dto.User;
import com.jerry.demo.usercenter.api.services.UserService;
import com.jerry.demo.usercenter.data.mapper.UserMapper;
import com.jerry.demo.usercenter.data.po.UserPO;
import com.jerry.demo.usercenter.services.utils.UserCacheNames;
import com.jerry.demo.usercenter.services.utils.aop.annotations.UserCacheEvict;
import com.jerry.demo.usercenter.services.utils.aop.annotations.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import java.util.Collection;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User createUser(String identifier, String credential, String nickname, Collection<String> authorities) {
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
    public User getUserByNickName(String nickname) {
        UserPO po = userMapper.selectByNickname(nickname);
        if (po != null) {
            return new User(po.getId(),po.getGmtCreate().getTime(), po.getNickname(), po.getAvatar());
        }

        return null;
    }

    @Override
    @UserCacheEvict(userCacheNames = {UserCacheNames.userCacheById, UserCacheNames.userCacheByNickname})
    public boolean updateAvatar(@UserId Long userId, String avatar) {
        final int rows = userMapper.update(userId, null, avatar);
        return rows > 0;
    }
}


