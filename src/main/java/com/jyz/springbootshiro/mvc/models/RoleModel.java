package com.jyz.springbootshiro.mvc.models;

import com.jyz.springbootshiro.mvc.entity.PermissionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleModel {
    private  int id;
    private  String  roleName;
    private  String roleDesc;
    private  List<PermissionEntity> perms;
}
