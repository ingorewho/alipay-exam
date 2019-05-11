package com.alipay.common.utils.access;

import com.alipay.common.response.ApiResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author renzhiqiang
 * @Description 用户接入token校验切面
 * @Date 2019-04-09
 **/
@Aspect
public class AccessTokenAspect {
    /**常量token.**/
    private static final String TOKEN_KEY = "token";
    /**常量id.**/
    private static final String ID_KEY = "id";

    @Around("@annotation(com.alipay.common.utils.access.AcessCheck)")
    public Object doAccessCheck(ProceedingJoinPoint joinPoint, AcessCheck acessCheck) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String id = request.getParameter("id");
        String token = request.getParameter("token");
        boolean result = verify(id,token);
        if(result){
            //执行连接点方法
            Object object = joinPoint.proceed();

            return object;
        }else {
            return ApiResponse.buildAccessError("token校验失败!");
        }
    }


    private boolean verify(String id, String token){
        //token校验机制暂未实现，实现方案有：JWT的token认证实现
        return true;
    }
}
