package com.jerry.demo.usercenter.test.mapper;

import com.jerry.demo.usercenter.api.dto.UserAuthInfo;
import com.jerry.demo.usercenter.api.enums.AuthType;
import com.jerry.demo.usercenter.data.mapper.UserAuthInfoMapper;
import com.jerry.demo.usercenter.data.po.UserAuthInfoPO;
import com.jerry.demo.usercenter.test.BaseJUnitTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserAuthInfoMapperTests extends BaseJUnitTests{

    @Autowired
    private UserAuthInfoMapper authInfoMapper;

    @Test
    @Transactional
    public void testInsert() {

        UserAuthInfoPO authInfo = new UserAuthInfoPO();
        authInfo.setGmtCreate(new Date());
        authInfo.setGmtModified(new Date());
        authInfo.setType(AuthType.USERNAME);
        authInfo.setIdentifier("root");
        authInfo.setCredential("123456");

        int rows = authInfoMapper.insert(authInfo);
        assertEquals(1, rows);

    }

    @Test
    @Transactional
    public void testSelect() {

        UserAuthInfoPO authInfo = new UserAuthInfoPO();
        authInfo.setGmtCreate(new Date());
        authInfo.setGmtModified(new Date());
        authInfo.setType(AuthType.USERNAME);
        authInfo.setIdentifier("zhcen");
        authInfo.setCredential("12345678");

        int rows = authInfoMapper.insert(authInfo);
        assertEquals(1, rows);

        UserAuthInfoPO ret = authInfoMapper.select(AuthType.USERNAME, "zhcen");
        assertNotNull(ret);
    }
}
