package com.alipay.transfer.schedule;

import com.alipay.common.request.TransferRequest;
import com.alipay.transfer.api.dao.TransferRecordDAO;
import com.alipay.transfer.api.entity.TransferRecord;
import com.alipay.transfer.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 定时扫描转账记录表任务
 * @Date 2019-04-10
 **/
@Component
public class ScheduledScanTransferRecord {
    @Autowired
    private TransferRecordDAO transferRecordDAO;
    @Autowired
    private AccountService accountService;

    /**
     * 每隔2秒执行一次扫描任务
     */
    @Scheduled(cron="2 * * * * ?")
    public void execute(){
        //1.根据状态为已扣款 去查询出转账记录
        List<TransferRecord> records = transferRecordDAO.select(TransferRecord.RecordStatus.DECREASE.getStatus());
        if (CollectionUtils.isEmpty(records)){
            //没有需要执行的任务
            return;
        }else {
            //存在需要自动付款的任务
            records.parallelStream().forEach(record -> accountService.increaseAmount(generate(record)));
        }
    }


    /***
     * 根据转账记录构建一个转账请求
     * @param record
     * @return
     */
    private TransferRequest generate(TransferRecord record){
        TransferRequest request = new TransferRequest.Builder()
                .amount(record.getAmount())
                .payAccount(record.getPayAccount())
                .reciveAccount(record.getReciveAccount())
                .transferId(record.getRecordId()).build();

        return request;
    }
}
