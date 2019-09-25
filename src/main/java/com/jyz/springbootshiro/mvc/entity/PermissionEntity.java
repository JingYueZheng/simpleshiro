package com.jyz.springbootshiro.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PermissionEntity {
    private  int id;
    private  String  type;
    private  String permissionDesc;
    private  String url;
    private  int parentId;
    private  String operation;
    private List<PermissionEntity> childerenPerm;
}
