package com.github.peterfei;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class LoggerAOPAspect {

    @Pointcut("execution(public * * (..))")
    public void mylogger(){}
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *  Around 环绕增强通知
     *
     * @param joinPoint 连接点，所有方法都属于连接点；但是当某些方法上使用了@PrintLog自定义注解时，
     *                  则其将连接点变为了切点；然后在切点上织入额外的增强处理；切点和其相应的增强处理构成了切面Aspect 。
     */
    @Around("@annotation(com.github.peterfei.LoggerAOP) && @annotation(loggerAOP) && mylogger()")
    public Object around(ProceedingJoinPoint point, LoggerAOP loggerAOP) throws Throwable{
        //开始时间
        long start = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        long end = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) point.getSignature();

        //请求的方法名
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.debug("【接口执行时间】接口名：{}.{},执行时间:{}毫秒",className,methodName,(end-start));
        log.info("【接口执行时间】接口名：{}.{},执行时间:{}毫秒",className,methodName,(end-start));
        return result;
    }

}
