package com.alipay.common.mock;

import com.alipay.common.message.event.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 账号mock接口
 * @Date 2019-04-10
 **/
@Component
public class AlipayGroupMock {

    /**
     * 根据群组id加载群成员id列表
     * @param groupId
     * @return
     */
    public List<Integer> loadAccountIds(int groupId){
        return new ArrayList<>();
    }

    public void adviceGroup(Event event){
        
    }
}
