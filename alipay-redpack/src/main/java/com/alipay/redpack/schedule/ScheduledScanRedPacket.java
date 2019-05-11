package com.alipay.redpack.schedule;

import com.alipay.redpack.api.dao.AccountDAO;
import com.alipay.redpack.api.dao.RedPackDAO;
import com.alipay.redpack.api.entity.RedPacket;
import com.alipay.redpack.api.service.RedPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 定时扫描红包信息，如果红包过期则收回
 * @Date 2019-04-10
 **/
@Component
public class ScheduledScanRedPacket {
    @Autowired
    private RedPackService redPackService;
    @Autowired
    private RedPackDAO redPackDAO;

    /**过期时间：默认1小时吧**/
    @Value("${redpack.expiretime:60}")
    private int expireTime;

    /**
     * 每隔30秒执行一次扫描任务
     */
    @Scheduled(cron="30 * * * * ?")
    public void execute(){
        //1.查询出已经过期的红包，红包实体记录了创建红包的时间，可以
        List<RedPacket> redPackets = redPackDAO.selectExpireRedPacket(expireTime * 60 * 1000L);
        if (CollectionUtils.isEmpty(redPackets)){
            //没有需要执行的任务
            return;
        }else {
            //存在需要回收的红包
            redPackets.parallelStream().forEach(redPacket -> redPackService.recoverRedPack(redPacket));
        }
    }
}
