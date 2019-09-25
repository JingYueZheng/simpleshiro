package com.jyz.springbootshiro.mvc.service;

import com.jyz.springbootshiro.mvc.entity.PermissionEntity;
import com.jyz.springbootshiro.mvc.entity.UserEntity;
import com.jyz.springbootshiro.mvc.models.RoleModel;
import com.jyz.springbootshiro.mvc.service.impl.UserServiceImpl;

import java.util.List;

public interface UserService {

    void saveUser(UserEntity userEntity);

    UserEntity selectByUserName(String username);

    List<PermissionEntity> selectMenuList(String username);

    List<RoleModel> selectRoles(String username);
}
