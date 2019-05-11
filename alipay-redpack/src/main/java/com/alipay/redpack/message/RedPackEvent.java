package com.alipay.redpack.message;

import com.alipay.common.message.event.AbstractEvent;
import com.alipay.redpack.api.entity.RedPacket;

/**
 * @Author renzhiqiang
 * @Description 红包事件
 * @Date 2019-04-10
 **/
public class RedPackEvent extends AbstractEvent<RedPacket> {
    public RedPackEvent(RedPacket redPacket, String name){
        super(redPacket, name);
    }

}
