package com.jerry.demo.usercenter.services.impl;

import com.jerry.demo.usercenter.api.dto.User;
import com.jerry.demo.usercenter.api.enums.AuthType;
import com.jerry.demo.usercenter.api.exception.AccountExistsException;
import com.jerry.demo.usercenter.api.exception.NicknameExistsException;
import com.jerry.demo.usercenter.api.services.UserService;
import com.jerry.demo.usercenter.data.mapper.UserAuthInfoMapper;
import com.jerry.demo.usercenter.data.mapper.UserMapper;
import com.jerry.demo.usercenter.data.po.UserAuthInfoPO;
import com.jerry.demo.usercenter.data.po.UserPO;
import com.jerry.demo.usercenter.services.cache.UserCacheNames;
import com.jerry.demo.usercenter.services.utils.ExceptionUtils;
import net.sf.ehcache.CacheManager;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAuthInfoMapper authInfoMapper;
    @Autowired
    private CacheManager cacheManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User createUser(String nickname
            , List<String> authorities
            , AuthType authType
            , String identifier
            , String credential) throws NicknameExistsException,AccountExistsException {

        UserPO userPO = new UserPO(nickname, null, authorities);
        try {
            userMapper.insert(userPO);
        } catch (Exception e) {
            if (!ExceptionUtils.hasDuplicateEntryException(e)) {
                throw e;
            } else {
                throw new NicknameExistsException();
            }
        }

        try {
            UserAuthInfoPO authInfoPO = new UserAuthInfoPO(authType
                    , identifier
                    , credential
                    , userPO.getId()
                    , authType != AuthType.EMAIL);
            authInfoMapper.insert(authInfoPO);
        } catch (Exception e) {
            if (!ExceptionUtils.hasDuplicateEntryException(e)) {
                throw e;
            } else {
                throw new AccountExistsException();
            }
        }

        userPO = userMapper.selectById(userPO.getId());
        return new User(userPO.getId(), userPO.getGmtCreate().getTime(), userPO.getNickname(), userPO.getAvatar(), userPO.getAuthorities());
    }

    @Override
    @Cacheable(value = UserCacheNames.userCacheById)
    public User getUserById(long userId) {
        UserPO po = userMapper.selectById(userId);
        if (po != null) {
            return new User(po.getId(), po.getGmtCreate().getTime(), po.getNickname(), po.getAvatar(), po.getAuthorities());
        }

        return null;
    }

    @Override
    @Cacheable(value = UserCacheNames.userCacheByNickname)
    public User getUserByNickName(String nickname) {
        UserPO po = userMapper.selectByNickname(nickname);
        if (po != null) {
            return new User(po.getId(), po.getGmtCreate().getTime(), po.getNickname(), po.getAvatar(), po.getAuthorities());
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

    @Override
    @Cacheable(value = UserCacheNames.userCacheById)
    public boolean updateAuthorities(Long userId, List<String> authorities) {
        return userMapper.updateAuthorities(userId, authorities) > 0;
    }


}


