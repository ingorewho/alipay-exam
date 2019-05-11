package com.alipay.transfer.api.service.impl;

import com.alipay.common.exception.ExceptionConst;
import com.alipay.common.exception.TransferException;
import com.alipay.common.request.TransferRequest;
import com.alipay.transfer.api.dao.AccountDAO;
import com.alipay.transfer.api.dao.TransferRecordDAO;
import com.alipay.transfer.api.entity.Account;
import com.alipay.transfer.api.entity.TransferRecord;
import com.alipay.transfer.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author renzhiqiang
 * @Description 账户操作接口实现类
 * @Date 2019-04-09
 **/
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private TransferRecordDAO transferRecordDAO;
    @Autowired
    private AccountDAO accountDAO;


    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean decreaseAmount(TransferRequest request) {
        //1.保证幂等性：使用主键约束来实现，主键是record_id，防止同一个交易号多次请求结果不同
        try{
            //转账记录有两个状态：1.已扣款 2.已付款，如果只扣款没付款，则通过定时任务去扫描这些未付款的记录，进行自动付款
            transferRecordDAO.save(generate(request, TransferRecord.RecordStatus.DECREASE.getStatus()));
        }catch (DuplicateKeyException e){
            return true;
        }catch (Exception e){
            //非主键冲突异常认为是失败操作
            return false;
        }

        //2.判断付款方账户上余额是否充足
        Account account = accountDAO.select(request.getPayAccount());
        if (account == null || account.getAmount() == null ||
                account.getAmount().compareTo(request.getAmount()) < 0){
            //账户余额不足
            throw new TransferException(ExceptionConst.BALANCE_LESS, "余额不足");
        }

        //3.进行扣款操作
        Account payAccount = new Account.Builder().name(request.getPayAccount()).amount(request.getAmount()).build();
        // 乐观锁用来处理并发情况下的重复扣款问题
        accountDAO.decreaseAmount(payAccount, account.getVersion());
        return true;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean increaseAmount(TransferRequest request) {
        //1.进行付款操作
        Account reciveAccount = new Account.Builder().name(request.getPayAccount()).amount(request.getAmount()).build();
        //先查一下这个账户是否存在，如果不存在则需要创建一个新用户
        Account account = accountDAO.select(request.getReciveAccount());
        if (account == null){
            accountDAO.save(reciveAccount);
        }else {
            accountDAO.increaseAmount(reciveAccount);
        }

        //2.更新转账记录状态为已付款
        transferRecordDAO.update(generate(request, TransferRecord.RecordStatus.INCREASE.getStatus()));
        return false;
    }


    /**
     * 生成一条转账记录
     * @param request
     * @return
     */
    private TransferRecord generate(TransferRequest request, int status){
        TransferRecord record = new TransferRecord();
        record.setPayAccount(request.getPayAccount());
        record.setRecordId(request.getTransferId());
        record.setReciveAccount(request.getReciveAccount());
        record.setStatus(status);
        return record;
    }

}
