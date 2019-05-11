package com.alipay.common.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author renzhiqiang
 * @Description 转账请求DTO
 * @Date 2019-04-09
 **/
public class TransferRequest implements Serializable {
    private static final long serialVersionUID = 3859644528708520604L;

    /**交易id.**/
    private int transferId;
    /**转帐金额.**/
    private BigDecimal amount;
    /**支付方.**/
    private String payAccount;
    /**收款方.**/
    private String reciveAccount;

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public String getReciveAccount() {
        return reciveAccount;
    }

    public void setReciveAccount(String reciveAccount) {
        this.reciveAccount = reciveAccount;
    }


    public TransferRequest(Builder builder){
        this.transferId = builder.transferId;
        this.amount = builder.amount;
        this.payAccount = builder.payAccount;
        this.reciveAccount = builder.reciveAccount;
    }

    public static class Builder{
        private int transferId;
        private BigDecimal amount;
        private String payAccount;
        private String reciveAccount;


        public Builder transferId(int transferId){
            this.transferId = transferId;
            return this;
        }
        public Builder amount(BigDecimal amount){
            this.amount = amount;
            return this;
        }
        public Builder payAccount(String  payAccount){
            this.payAccount = payAccount;
            return this;
        }
        public Builder reciveAccount(String  reciveAccount){
            this.reciveAccount = reciveAccount;
            return this;
        }

        public TransferRequest build(){
            return new TransferRequest(this);
        }
    }
}
