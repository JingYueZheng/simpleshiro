package com.jyz.springbootshiro.mvc.entity;

import com.jyz.springbootshiro.utils.RandomSoltUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 4603642343377807741L;

    @ApiModelProperty(value = "用户名" ,dataType="String" ,required = true)
    private  String username;
    @ApiModelProperty(value = "密码" ,dataType="String" ,required = true)
    private  String password;
    @ApiModelProperty(value = "盐" ,dataType="String" ,required = false)
    private  String solt = RandomSoltUtils.RandomSolt();
    private  Boolean rememberMe ;
}
