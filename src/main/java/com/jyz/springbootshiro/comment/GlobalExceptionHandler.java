package com.jyz.springbootshiro.comment;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 全局异常捕获  未授权异常，提示未授权
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public RespApi  exceptionHandler(Exception e){
         log.info(e.getMessage());
         return  RespApi.operateError("抱歉，你没有权限");
    }
}
