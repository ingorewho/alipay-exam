package com.alipay.common.exception;

/**
 * @Author renzhiqiang
 * @Description 转账异常
 * @Date 2019-04-10
 **/
public class TransferException extends RuntimeException{
    /**异常码.**/
    private int code;
    /**异常信息.**/
    private String msg;

    public TransferException(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public TransferException(){

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
