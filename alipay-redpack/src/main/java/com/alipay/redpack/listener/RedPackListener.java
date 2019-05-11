package com.alipay.redpack.listener;

import com.alipay.common.message.event.Event;
import com.alipay.common.message.listener.EventListener;
import com.alipay.common.message.publisher.EventPublisher;
import com.alipay.common.mock.AlipayGroupMock;
import com.alipay.redpack.api.entity.RedPacket;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author renzhiqiang
 * @Description 红包事件监听器
 * @Date 2019-04-10
 **/
@Component
public class RedPackListener implements EventListener<RedPacket>, InitializingBean {
    @Autowired
    private AlipayGroupMock alipayGroupMock;
    @Autowired
    private EventPublisher eventPublisher;

    @Override
    public void listen(Event<RedPacket> event) {
        //调用mock接口发送给群组用户
        alipayGroupMock.adviceGroup(event);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        eventPublisher.registListener(this);
    }
}
