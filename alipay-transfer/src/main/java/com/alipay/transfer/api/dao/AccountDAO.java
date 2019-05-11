package com.alipay.transfer.api.dao;

import com.alipay.transfer.api.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author renzhiqiang
 * @Description 账户操作数据库接入层
 * @Date 2019-04-09
 **/
@Mapper
public interface AccountDAO {
    /**
     * 创建一个用户
     * @param account
     * @return
     */
    int save(Account account);

    /**
     * 根据名称获取账户信息(名称是唯一的)
     * @param name 账户名称
     * @return
     */
    Account select(@Param(value = "name") String name);

    /**
     * 对账户进行扣款(通过版本号实现乐观锁)
     * @param account 付款方
     * @param version 版本号
     * @return
     */
    int decreaseAmount(Account account, @Param(value = "version") int version);


    /**
     * 对账户进行付款
     * @param account 收款方
     * @return
     */
    int increaseAmount(Account account);
}
