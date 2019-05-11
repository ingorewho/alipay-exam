package com.alipay.transfer.api.service;

import com.alipay.common.request.TransferRequest;

/**
 * @Author renzhiqiang
 * @Description 账户操作接口定义
 * @Date 2019-04-09
 **/
public interface AccountService {

    /**
     * 对账户进行扣款
     * @param request
     * @return
     */
    boolean decreaseAmount(TransferRequest request);


    /**
     * 对账户进行付款
     * @param request
     * @return
     */
    boolean increaseAmount(TransferRequest request);
}
