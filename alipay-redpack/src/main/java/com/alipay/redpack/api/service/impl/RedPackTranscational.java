package com.alipay.redpack.api.service.impl;

import com.alipay.redpack.api.dao.AccountDAO;
import com.alipay.redpack.api.dao.RedPackDAO;
import com.alipay.redpack.api.entity.Account;
import com.alipay.redpack.api.entity.RedPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author renzhiqiang
 * @Description 红包操作事务类
 * @Date 2019-04-10
 **/
@Component
public class RedPackTranscational {
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private RedPackDAO redPackDAO;

    @Transactional(rollbackFor = Throwable.class)
    public void saveOrUpdate(Account account, RedPacket redPacket){
        //1.更新账户金额
        accountDAO.update(account);

        //2.保存红包到数据库
        redPackDAO.save(redPacket);
    }
}
