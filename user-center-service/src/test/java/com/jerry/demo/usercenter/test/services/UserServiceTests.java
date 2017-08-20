package com.jerry.demo.usercenter.test.services;

import com.jerry.demo.usercenter.api.dto.User;
import com.jerry.demo.usercenter.api.enums.AuthType;
import com.jerry.demo.usercenter.api.services.UserService;
import com.jerry.demo.usercenter.data.mapper.UserMapper;
import com.jerry.demo.usercenter.data.po.UserPO;
import com.jerry.demo.usercenter.test.BaseJUnitTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserServiceTests extends BaseJUnitTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void testCreateUser() throws Exception {

        try {
            userService.createUser("zhcen"
                    , Arrays.asList("ROLE_USER")
                    , AuthType.USERNAME
                    , "zhcen"
                    , "123456");
        } catch (Exception e) {
            throw e;
        }

        assertTrue(true);
    }

    @Test
    @Transactional
    public void testGetUser() {

        UserPO userPO = new UserPO();
        userPO.setGmtCreate(new Date());
        userPO.setGmtModified(new Date());
        userPO.setNickname("John Mick");
        userPO.setAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3641777042,4062717379&fm=26&gp=0.jpg");

        int rows = userMapper.insert(userPO);
        assertEquals(1, rows);

        User ret = userService.getUserById(userPO.getId());
        assertNotNull(ret);
    }

    @Test
    @Transactional
    public void testUpdate() {

        UserPO userPO = new UserPO();
        userPO.setGmtCreate(new Date());
        userPO.setGmtModified(new Date());
        userPO.setNickname("John");
        userPO.setAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3641777042,4062717379&fm=26&gp=0.jpg");

        int rows = userMapper.insert(userPO);
        assertEquals(1, rows);

        User user = new User(userPO.getId(), System.currentTimeMillis(), "John Mick", userPO.getAvatar(), userPO.getAuthorities());
        boolean ret = userService.updateAvatar(user.getId(), user.getAvatar());
        assertTrue(ret);

        ret = userService.updateAuthorities(user.getId(), Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
        assertTrue(ret);
    }

}
