package com.jyz.springbootshiro.mvc.service.impl;
import com.jyz.springbootshiro.mvc.dao.UserDao;
import com.jyz.springbootshiro.mvc.entity.PermissionEntity;
import com.jyz.springbootshiro.mvc.entity.UserEntity;
import com.jyz.springbootshiro.mvc.models.RoleModel;
import com.jyz.springbootshiro.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void saveUser(UserEntity userEntity) {
        System.out.println(userEntity);
         userDao.saveUser(userEntity);
    }

    @Override
    public UserEntity selectByUserName(String username) {
        return userDao.selectByUserName(username);
    }

    @Override
    public List<PermissionEntity> selectMenuList(String username) {
        return userDao.selectMenuList(username);
    }

    @Override
    public List<RoleModel> selectRoles(String username) {

        return userDao.selectRoles(username);
    }
}
