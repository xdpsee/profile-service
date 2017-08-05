package com.jerry.demo.usercenter.test.mapper;

import com.jerry.demo.usercenter.data.mapper.UserMapper;
import com.jerry.demo.usercenter.data.po.UserPO;
import com.jerry.demo.usercenter.test.BaseJUnitTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserMapperTests extends BaseJUnitTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {

        UserPO user = new UserPO();
        user.setGmtCreate(new Date());
        user.setGmtModified(new Date());
        user.setNickname("John");
        user.setAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3641777042,4062717379&fm=26&gp=0.jpg");

        int rows = userMapper.insert(user);
        assertEquals(1, rows);

    }

    @Test
    public void testUpdate() {
        UserPO user = new UserPO();
        user.setGmtCreate(new Date());
        user.setGmtModified(new Date());
        user.setNickname("Jackson");
        user.setAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3641777042,4062717379&fm=26&gp=0.jpg");

        int rows = userMapper.insert(user);
        assertEquals(1, rows);

        userMapper.update(user.getId(), "Micheal Jackson", null);

        user = userMapper.selectById(user.getId());
        assertNotNull(user);
        assertEquals("Micheal Jackson", user.getNickname());

    }

}
