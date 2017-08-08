package com.jerry.demo.usercenter.test.services;

import com.jerry.demo.usercenter.api.dto.User;
import com.jerry.demo.usercenter.api.services.UserService;
import com.jerry.demo.usercenter.data.mapper.UserMapper;
import com.jerry.demo.usercenter.data.po.UserPO;
import com.jerry.demo.usercenter.test.BaseJUnitTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

        User user = new User(userPO.getId(), "John Mick", userPO.getAvatar());
        boolean ret = userService.update(user);
        assertTrue(ret);

        System.out.println("....");
    }



}