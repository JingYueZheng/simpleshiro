package com.jyz.springbootshiro.utils;

import com.jyz.springbootshiro.mvc.entity.UserEntity;
import org.apache.shiro.crypto.hash.SimpleHash;

public class ShiroUtils {

    public static String  encryPassword(UserEntity userEntity){
        //可以优化，通过读取配置文件 更加灵活 可维护
        SimpleHash simpleHash = new SimpleHash("md5", userEntity.getPassword().getBytes(), userEntity.getSolt(), 2);
        String s = simpleHash.toHex();
        return  s;
    }
}
