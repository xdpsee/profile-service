package com.jerry.demo.usercenter.services.utils.aop.aspect;

import com.jerry.demo.usercenter.api.dto.User;
import com.jerry.demo.usercenter.services.utils.UserCacheNames;
import com.jerry.demo.usercenter.services.utils.aop.annotations.UserCacheEvict;
import net.sf.ehcache.CacheManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
@Aspect
public class UserCacheEvictAspect {

    @Autowired
    private CacheManager cacheManager;

    @AfterReturning("execution(* com.jerry.demo.usercenter.services.impl.UserInfoServiceImpl.*(..)) && " +
            "(@annotation(evict))")
    public void after(JoinPoint joinPoint, UserCacheEvict evict) {
        final User user = (User) joinPoint.getArgs()[0];
        evictUserCache(evict, user);
    }

    private void evictUserCache(UserCacheEvict evict, User user) {
        String[] userCacheNames = evict.userCacheNames();

        for (String userCacheName : userCacheNames) {
            if (UserCacheNames.userCacheById.equals(userCacheName)) {
                cacheManager.getCache(userCacheName).remove(user.getId());
            }

            if (UserCacheNames.userCacheByNickname.equals(userCacheName)) {
                cacheManager.getCache(userCacheName).remove(user.getNickname());
            }
        }

    }
}
