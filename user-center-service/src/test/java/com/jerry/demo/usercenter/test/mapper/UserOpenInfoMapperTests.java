package com.jerry.demo.usercenter.test.mapper;

import com.jerry.demo.usercenter.api.enums.SocialType;
import com.jerry.demo.usercenter.data.mapper.UserOpenInfoMapper;
import com.jerry.demo.usercenter.data.po.UserOpenInfoPO;
import com.jerry.demo.usercenter.test.BaseJUnitTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserOpenInfoMapperTests extends BaseJUnitTests {

    @Autowired
    private UserOpenInfoMapper openInfoMapper;

    @Test
    public void testInsert() {

        UserOpenInfoPO openInfo = new UserOpenInfoPO();
        openInfo.setType(SocialType.QQ);
        openInfo.setIdentifier("274425775");
        openInfo.setOpenId(12340098L);
        openInfo.setNickname("NULL");
        openInfo.setAvatar("");

        assertEquals(1, openInfoMapper.insert(openInfo));

    }

    @Test
    public void testSelect() {

        UserOpenInfoPO openInfo = new UserOpenInfoPO();
        openInfo.setType(SocialType.WEIBO);
        openInfo.setIdentifier("FunnySun");
        openInfo.setOpenId(12340L);
        openInfo.setNickname("HUI-EE");
        openInfo.setAvatar("");

        assertEquals(1, openInfoMapper.insert(openInfo));

        openInfo = openInfoMapper.select(SocialType.WEIBO, 12340L);
        assertNotNull(openInfo);

    }

}
