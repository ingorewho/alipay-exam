package com.alipay.transfer.api.service;

import com.alipay.common.request.TransferRequest;

/**
 * @Author renzhiqiang
 * @Description 转账接口定义
 * @Date 2019-04-09
 **/
public interface TransferService {
    /**
     * 转账接口
     * @param request
     * @return
     */
    boolean transfer(TransferRequest request);
}
