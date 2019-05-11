package com.alipay.redpack.api.entity;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @Author renzhiqiang
 * @Description 红包实体
 * @Date 2019-04-10
 **/
public class RedPacket {
    /**主键.**/
    private String id;
    /**群组id.**/
    private int groupId;
    /**生成时间**/
    private long timestamp;
    /**发红包的账户id.**/
    private int  accountId;
    /**祝福语.**/
    private String remark;
    /**红包数组.**/
    private RedPacket.RedPacketItem[] redPacketItemArr;
    /**剩余个数.**/
    private int leftCount;
    /**版本号.**/
    private int version;

    public RedPacket(int accountId, int size, String remark, int leftCount, int groupId) {
        this.accountId = accountId;
        this.remark = remark;
        this.id = UUID.randomUUID().toString();
        this.redPacketItemArr = new RedPacket.RedPacketItem[size];
        this.timestamp = System.currentTimeMillis();
        this.leftCount = leftCount;
        this.groupId = groupId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public RedPacketItem[] getRedPacketItemArr() {
        return redPacketItemArr;
    }

    public void setRedPacketItemArr(RedPacketItem[] redPacketItemArr) {
        this.redPacketItemArr = redPacketItemArr;
    }

    public int getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(int leftCount) {
        this.leftCount = leftCount;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * 单个红包item
     */
    public class RedPacketItem {

        /**
         * 该红包领到的金额
         */
        private BigDecimal amount;
        /**
         * 领到的用户
         */
        private int accountId;
        /**
         * 领到的时间
         */
        private long timestamp;

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }
}
