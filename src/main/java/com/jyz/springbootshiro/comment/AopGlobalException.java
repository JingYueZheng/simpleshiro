package com.jyz.springbootshiro.comment;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.xml.crypto.dsig.SignatureMethod;
import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
public class AopGlobalException {

    @Pointcut("@annotation(com.jyz.springbootshiro.comment.Logging)")
    public void pointcut() { }


    @Around("pointcut()")
    public Object afterThrowing(ProceedingJoinPoint joinPoint) {
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            log.error(className+"."+methodName+"["+joinPoint.getArgs()+"]");
            log.error(signature.getMethod().getAnnotation(Logging.class).value());
            log.error(throwable.getMessage());
        }
        return  RespApi.operateError();
    }
}
