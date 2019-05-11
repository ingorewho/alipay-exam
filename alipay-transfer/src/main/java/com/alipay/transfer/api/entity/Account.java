package com.alipay.transfer.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author renzhiqiang
 * @Description 账户信息实体
 * @Date 2019-04-10
 **/
public class Account implements Serializable {
    private static final long serialVersionUID = 7469448458938614767L;
    /**主键id.**/
    private int id;
    /**账户名称.**/
    private String name;
    /**账户余额.**/
    private BigDecimal amount;
    /**版本号信息(用于实现乐观锁).**/
    private int version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Account(Builder builder){
        this.name = builder.name;
        this.amount = builder.amount;
    }

    public static class Builder{
        private BigDecimal amount;
        private String name;


        public Account.Builder amount(BigDecimal amount){
            this.amount = amount;
            return this;
        }
        public Account.Builder name(String  name){
            this.name = name;
            return this;
        }

        public Account build(){
            return new Account(this);
        }
    }
}
