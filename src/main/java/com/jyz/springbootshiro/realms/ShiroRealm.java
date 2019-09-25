package com.jyz.springbootshiro.realms;

import com.jyz.springbootshiro.mvc.entity.UserEntity;
import com.jyz.springbootshiro.mvc.models.RoleModel;
import com.jyz.springbootshiro.mvc.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;


public class ShiroRealm extends AuthorizingRealm {
    //重写这个方法可以使自定义错误信息
    @Override
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
        CredentialsMatcher cm = this.getCredentialsMatcher();
        if (cm != null) {
            if (!cm.doCredentialsMatch(token, info)) {
                String msg = "用户名或密码错误";
                throw new IncorrectCredentialsException(msg);
            }
        } else {
            throw new AuthenticationException("A CredentialsMatcher must be configured in order to verify credentials during authentication.  If you do not wish for credentials to be examined, you can configure an " + AllowAllCredentialsMatcher.class.getName() + " instance.");
        }
    }

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户的角色 和  角色的权限
        String username = (String)principalCollection.getPrimaryPrincipal();
        System.out.println("用户" + username + "获取权限-----ShiroRealm.doGetAuthorizationInfo");
        List<RoleModel> roles =  userService.selectRoles(username);
        HashSet roleset = new HashSet();
        roles.stream().forEach(item->{
            roleset.add(item.getRoleName());
        });

        HashSet permissionSet = new HashSet();
        roles.stream().forEach(item->{
            item.getPerms().stream().forEach(perm->{
                permissionSet.add(perm.getOperation());
            });
        });
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleset);
        info.setStringPermissions(permissionSet);
        return info;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)throws AuthenticationException {
        String principal =(String) authenticationToken.getPrincipal();
        UserEntity user = userService.selectByUserName(principal);
        if (user==null||!principal.equals(user.getUsername())){
             throw  new UnknownAccountException("用户名或密码错误");
        }
        return new SimpleAuthenticationInfo(principal,user.getPassword(), ByteSource.Util.bytes(user.getSolt()),this.getClass().getName());
    }
}
