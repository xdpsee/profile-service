package com.jerry.demo.usercenter.services.impl;

import com.jerry.demo.usercenter.api.dto.UserAuthInfo;
import com.jerry.demo.usercenter.api.enums.AuthType;
import com.jerry.demo.usercenter.api.exception.UserBindingException;
import com.jerry.demo.usercenter.api.exception.UserNotFoundException;
import com.jerry.demo.usercenter.api.services.UserAuthInfoService;
import com.jerry.demo.usercenter.data.mapper.UserAuthInfoMapper;
import com.jerry.demo.usercenter.data.mapper.UserMapper;
import com.jerry.demo.usercenter.data.po.UserAuthInfoPO;
import com.jerry.demo.usercenter.data.po.UserPO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserAuthInfoServiceImpl implements UserAuthInfoService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAuthInfoMapper authInfoMapper;

    @Override
    public UserAuthInfo getAuthInfo(AuthType type, String identifier) {

        final UserAuthInfo result = new UserAuthInfo();

        UserAuthInfoPO authInfoPO = authInfoMapper.select(type, identifier);
        if (authInfoPO != null) {

            BeanUtils.copyProperties(authInfoPO, result);

            if (authInfoPO.getUserId() > 0) {
                UserPO userPO = userMapper.selectById(authInfoPO.getUserId());
                if (userPO != null) {
                    result.setAuthorities(userPO.getAuthorities());
                }
            }

            return result;
        }

        return null;
    }

    @Override
    public boolean updateCredential(AuthType authType, String identifier, String credential) {

        return authInfoMapper.updateCredential(authType, identifier, credential) > 0;
    }

    @Override
    public boolean bindUser(AuthType type, String identifier, long userId)
            throws UserNotFoundException, UserBindingException {

        final UserAuthInfoPO authInfo = authInfoMapper.select(type, identifier);
        if (null == authInfo) {
            throw new UserNotFoundException("认证信息不存在");
        }

        final UserPO user = userMapper.selectById(userId);
        if (null == user) {
            throw new UserNotFoundException("用户不存在");
        }

        if (authInfo.getUserId() > 0 && authInfo.getUserId() != userId) {
            throw new UserBindingException("用户已绑定");
        }

        return authInfoMapper.updateUserId(type, identifier, userId) > 0;

    }
}

