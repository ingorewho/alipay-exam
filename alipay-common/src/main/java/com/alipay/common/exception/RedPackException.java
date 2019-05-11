package com.alipay.common.exception;

/**
 * @Author renzhiqiang
 * @Description 红包异常类定义
 * @Date 2019-04-10
 **/
public class RedPackException extends RuntimeException{
    /**异常码.**/
    private int code;
    /**异常信息.**/
    private String msg;

    public RedPackException(String msg){
        this.msg = msg;
    }

    public RedPackException(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public RedPackException(){

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
