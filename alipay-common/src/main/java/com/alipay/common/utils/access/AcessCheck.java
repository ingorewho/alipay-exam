package com.alipay.common.utils.access;

/**
 * @Author renzhiqiang
 * @Description 接入检查注解
 * @Date 2019-04-09
 **/
public @interface AcessCheck {
    String desc() default "";

    boolean required() default true;
}
