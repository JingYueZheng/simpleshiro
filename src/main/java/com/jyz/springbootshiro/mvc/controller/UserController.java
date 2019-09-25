package com.jyz.springbootshiro.mvc.controller;

import com.jyz.springbootshiro.comment.Logging;
import com.jyz.springbootshiro.comment.RespApi;
import com.jyz.springbootshiro.event.TestEvent;
import com.jyz.springbootshiro.mvc.entity.PermissionEntity;
import com.jyz.springbootshiro.mvc.entity.UserEntity;
import com.jyz.springbootshiro.mvc.service.UserService;
import com.jyz.springbootshiro.utils.ShiroUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.reflect.NoSuchPointcutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    public ApplicationContext applicationContext;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录",notes = "用户名、密码必传，且为字符串")
    public RespApi userLogin( @RequestBody UserEntity userEntity){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername(userEntity.getUsername());
        token.setPassword(userEntity.getPassword().toCharArray());
        token.setRememberMe(userEntity.getRememberMe());
        try {
            subject.login(token);
        }catch (AuthenticationException e){
            RespApi respApi = RespApi.operateError(e.getMessage());
            return  respApi;
        }
        if (!subject.isAuthenticated()){
            return RespApi.operateError("验证失败");
        }
        return  RespApi.operateOk("验证成功");

    }

    @PostMapping("/save")
    public   RespApi  userSave(@RequestBody UserEntity userEntity) throws NoSuchPointcutException {
        UserEntity user = userService.selectByUserName(userEntity.getUsername());
        if (user!=null){
            RespApi respApi = RespApi.operateError("用户名重复");
            return  respApi;
        }
        userEntity.setPassword(ShiroUtils.encryPassword(userEntity));
        userService.saveUser(userEntity);
        return  RespApi.operateOk("注册成功");
    }

    @RequestMapping("/menuList")
    public RespApi menuList(){
        List<PermissionEntity> permissionEntities;
         try {
             String username =(String) SecurityUtils.getSubject().getPrincipal();
              permissionEntities = userService.selectMenuList(username);
         }catch (Exception e){
             e.printStackTrace();
             return  RespApi.operateError("服务器出错");
         }
         return  RespApi.operateOk(permissionEntities);
    }



    /**
     *   测试权限分配是否正确
     */
    @RequestMapping("/getmsg")
    @RequiresPermissions("users:asdfasdf")
    public String getMsg(){
        return "您拥有用户S 的 xxxx的权限权限";
    }


    @RequestMapping("/view")
    @RequiresPermissions("user:view")
    public String userView(){
        return "您拥有用户查看权限";
    }


    @RequestMapping("/update")
    @RequiresPermissions("user:update")
    public String userUpdate(){
        return "您拥有用户修改权限";
    }


    @RequestMapping("/delete")
    @RequiresPermissions("user:delete")
    public String userDelete(){
        return "您拥有用户删除权限";
    }

    /**
     *  测试监听器
     */
    @RequestMapping("/testLis")
    public String testListener(){
        TestEvent event = new TestEvent("zhangsan",this);
        applicationContext.publishEvent(event);
        event.setAge("asdfasdf");
        //当这个语句执行的时候会执行listener的onApplicationEvent 方法 或者是
        applicationContext.publishEvent(event);
        return  "success";

    }
}
