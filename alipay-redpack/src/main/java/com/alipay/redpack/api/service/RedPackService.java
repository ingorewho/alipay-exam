package com.alipay.redpack.api.service;

import com.alipay.common.request.RedPackRequest;
import com.alipay.redpack.api.entity.RedPacket;

import java.math.BigDecimal;

/**
 * @Author renzhiqiang
 * @Description 红包服务接口
 * @Date 2019-04-10
 **/
public interface RedPackService {

    /**
     * 创建一个红包接口
     * @param request
     * @return 红包实体
     */
    RedPacket createRedPack(RedPackRequest request);

    /**
     * 抢红包接口
     * @param accountId
     * @param redPackId
     * @return 红包金额
     */
    BigDecimal grabRedPack(int accountId, String redPackId);


    /**
     * 回收红包
     * @param redPacket
     * @return
     */
    boolean recoverRedPack(RedPacket redPacket);
}
