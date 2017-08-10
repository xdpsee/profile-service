package com.jerry.demo.usercenter.services.impl;

import com.jerry.demo.usercenter.api.dto.User;
import com.jerry.demo.usercenter.api.services.UserService;
import com.jerry.demo.usercenter.data.mapper.UserMapper;
import com.jerry.demo.usercenter.data.po.UserPO;
import com.jerry.demo.usercenter.services.cache.UserCacheNames;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CacheManager cacheManager;

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
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAvatar(Long userId, String avatar) {
        final int rows = userMapper.update(userId, null, avatar);
        if (rows > 0) {
            UserPO user = userMapper.selectById(userId);
            cacheManager.getCache(UserCacheNames.userCacheById).remove(userId);
            cacheManager.getCache(UserCacheNames.userCacheByNickname).remove(user.getNickname());
        }

        return rows > 0;
    }
}


