package com.alipay.common.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author renzhiqiang
 * @Description 红包请求
 * @Date 2019-04-10
 **/
public class RedPackRequest implements Serializable {
    private static final long serialVersionUID = 1621755597879071829L;
    /**发红包账户id.**/
    private int accountId;
    /**红包总金额.**/
    private BigDecimal amount;
    /**红包id.**/
    private int redPackId;
    /**红包数量.**/
    private int redPackCount;
    /**红包祝福语.**/
    private String remark;
    /**群组id.**/
    private int groupId;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getRedPackId() {
        return redPackId;
    }

    public void setRedPackId(int redPackId) {
        this.redPackId = redPackId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getRedPackCount() {
        return redPackCount;
    }

    public void setRedPackCount(int redPackCount) {
        this.redPackCount = redPackCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
