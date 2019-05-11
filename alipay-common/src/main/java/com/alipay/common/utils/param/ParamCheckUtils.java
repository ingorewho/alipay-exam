package com.alipay.common.utils.param;

/**
 * @Author renzhiqiang
 * @Description 参数检查公共类
 * @Date 2019-04-10
 **/
public final class ParamCheckUtils {

    /**
     * 只做简单null校验
     * @param params
     * @return
     */
    public static boolean checkValid(Object ... params){
        for (Object param : params){
            if (param == null){
                return false;
            }
        }
        return true;
    }
}
