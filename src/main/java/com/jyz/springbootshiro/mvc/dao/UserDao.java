package com.jyz.springbootshiro.mvc.dao;

import com.jyz.springbootshiro.mvc.entity.PermissionEntity;
import com.jyz.springbootshiro.mvc.entity.UserEntity;
import com.jyz.springbootshiro.mvc.models.RoleModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    void saveUser(UserEntity userEntity);

    UserEntity selectByUserName(String username);

    List<PermissionEntity> selectMenuList(String username);

    List<PermissionEntity> selectChilderenPerm(int id);

    List<RoleModel> selectRoles(String username);

    List<PermissionEntity> getRolePerm(String rid);
}
