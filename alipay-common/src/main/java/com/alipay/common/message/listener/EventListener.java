package com.alipay.common.message.listener;

import com.alipay.common.message.event.Event;

/**
 * @Author renzhiqiang
 * @Description 账号mock接口
 * @Date 2019-04-10
 **/
public interface EventListener<T> {

    void listen(Event<T> event);
}
