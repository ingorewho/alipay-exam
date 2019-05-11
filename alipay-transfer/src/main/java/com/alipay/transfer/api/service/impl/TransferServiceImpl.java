package com.alipay.transfer.api.service.impl;

import com.alipay.common.request.TransferRequest;
import com.alipay.transfer.api.service.AccountService;
import com.alipay.transfer.api.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author renzhiqiang
 * @Description 转账接口实现
 * @Date 2019-04-09
 **/
@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountService accountService;
    /***
     * 照理说应该拆分为生产服务和消费服务
     * 现在模拟简单的场景：在同一台服务器上，对两个不同数据库实例下的账户表进行操作
     * 为了资金安全：
     * 1.对付款方先进行扣款
     * 2.扣款成功，然后对收款方进行付款
     *
     * @param request
     * @return
     */
    @Override
    public boolean transfer(TransferRequest request) {
        //注：幂等性和并发控制在内部逻辑中实现

        //1.先执行扣款操作
        boolean result = accountService.decreaseAmount(request);

        if (result){
            //2.再执行付款操作
            return accountService.increaseAmount(request);
        }else {
            return false;
        }
    }


}
