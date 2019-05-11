package com.alipay.transfer.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author renzhiqiang
 * @Description 转账记录实体
 * @Date 2019-04-09
 **/
public class TransferRecord implements Serializable {

    private static final long serialVersionUID = -3530879670910351920L;
    /**主键id.**/
    private int id;
    /**转账记录id.**/
    private int recordId;
    /**转账状态.**/
    private int status;
    /**付款方.**/
    private String payAccount;
    /**收款方.**/
    private String reciveAccount;
    /**转账金额.**/
    private BigDecimal amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransferRecord{" +
                "recordId=" + recordId +
                ", status=" + status +
                ", payAccount='" + payAccount + '\'' +
                ", reciveAccount='" + reciveAccount + '\'' +
                ", amount=" + amount +
                '}';
    }

    public enum RecordStatus{
        /**已扣款状态.**/
        DECREASE(1),

        /**已付款状态.**/
        INCREASE(2);

        private int status;

        private RecordStatus(int status){
            this.status = status;
        }

        public int getStatus() {
            return status;
        }
    }
}
