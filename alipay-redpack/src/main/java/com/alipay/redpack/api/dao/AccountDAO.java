package com.alipay.redpack.api.dao;

import com.alipay.redpack.api.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author renzhiqiang
 * @Description 用户账户数据接入层
 * @Date 2019-04-10
 **/
@Mapper
public interface AccountDAO {

    /**
     * 根据名称获取账户信息(名称是唯一的)
     * @param accountId 账户名称
     * @return
     */
    Account select(@Param(value = "accountId") int accountId);

    /**
     * 更新用户记录.
     * @param account
     * @return
     */
    int update(Account account);
}
