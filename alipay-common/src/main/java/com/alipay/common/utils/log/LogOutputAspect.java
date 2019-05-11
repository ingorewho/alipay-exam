package com.alipay.common.utils.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author renzhiqiang
 * @Description 日志输出切面
 * @Date 2019-04-10
 **/
@Aspect
public class LogOutputAspect {
    private Logger logger = LoggerFactory.getLogger(LogOutputAspect.class);

    @Around("@annotation(logOutput)")
    public Object doLogOutput(ProceedingJoinPoint joinPoint, LogOutput logOutput) throws Throwable{
        String signatureName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        long start = System.currentTimeMillis();
        //执行连接点方法
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        if (logger.isDebugEnabled()){
            if (logOutput.printArgs()){
                logger.debug(String.format("方法名：%s，参数：%s，耗时：%s", signatureName, args, (end - start)));
            }else {
                //参数很长的情况下可能会配置不打印参数
                logger.debug(String.format("方法名：%s，耗时：%s", signatureName, args, (end - start)));
            }
        }
        return result;
    }
}
