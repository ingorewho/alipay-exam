package com.alipay.common.utils.log;

/**
 * @Author renzhiqiang
 * @Description 日志输出注解
 * @Date 2019-04-09
 **/
public @interface LogOutput {
    /**日志描述.**/
    String desc() default "";
    /**是否打印耗时.**/
    boolean printTime() default true;
    /**是否打印参数.**/
    boolean printArgs() default true;
}
