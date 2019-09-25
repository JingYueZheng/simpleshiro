package com.jyz.springbootshiro.config;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import java.util.concurrent.atomic.AtomicInteger;

public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private EhCacheManager cacheManager;

    private  int loginCount;

    public  void  setLoginCount(int loginCount){
        this.loginCount = loginCount;
    }

    public MyHashedCredentialsMatcher(EhCacheManager cacheManager){
        this.cacheManager = cacheManager;
    }
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Cache<String, AtomicInteger> passwordRetryCache = cacheManager.getCache("passwordRetryCache");
        String username = (String)token.getPrincipal();
        //retry count + 1
        AtomicInteger count = passwordRetryCache.get(username);
        if(count == null) {
            count =  new AtomicInteger(0);
            passwordRetryCache.put(username,count);
        }
        AtomicInteger retryCount = passwordRetryCache.get(username);
        //retryCount.incrementAndGet  使登录次数增加并得到增加后的值
        if(retryCount.incrementAndGet() > loginCount) {
            //if retry count > 5 throw
            throw new AuthenticationException("登录超过次数，十分钟之后再试试吧");
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            //clear retry count
            passwordRetryCache.remove(username);
        }
        return matches;
    }
}
