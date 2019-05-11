package com.alipay.transfer.api.dao;

import com.alipay.transfer.api.entity.TransferRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 转账操作记录数据库接入层
 * @Date 2019-04-09
 **/
@Mapper
public interface TransferRecordDAO {
    /**
     * 保存一条转账记录.
     * @param record
     * @return
     */
    int save(TransferRecord record);

    /**
     * 更新一条转账记录.
     * @param record
     * @return
     */
    int update(TransferRecord record);

    /**
     * 根据状态查询转账记录(这里没有做成分批，后续可以改进)
     * @param status
     * @return
     */
    List<TransferRecord> select(@Param(value = "status") int status);
}
