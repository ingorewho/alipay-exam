package com.alipay.common.mq.rocketmq.processor;

import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * @Author renzhiqiang
 * @Description 消息执行器接口
 * @Date 2019-04-10
 **/
public interface MessageProcessor {

    /**
     * 处理消息接口
     * @param messageExt
     * @return
     */
    boolean handleMessage(MessageExt messageExt);
}
