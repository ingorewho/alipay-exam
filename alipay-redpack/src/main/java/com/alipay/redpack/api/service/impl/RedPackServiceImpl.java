package com.alipay.redpack.api.service.impl;

import com.alipay.common.constant.RedPackConst;
import com.alipay.common.exception.RedPackException;
import com.alipay.common.message.publisher.EventPublisher;
import com.alipay.common.request.RedPackRequest;
import com.alipay.redpack.api.dao.AccountDAO;
import com.alipay.redpack.api.dao.RedPackDAO;
import com.alipay.redpack.api.entity.Account;
import com.alipay.redpack.api.entity.RedPacket;
import com.alipay.redpack.api.service.RedPackService;
import com.alipay.redpack.message.RedPackEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author renzhiqiang
 * @Description 红包服务实现类
 * @Date 2019-04-10
 **/
@Service
public class RedPackServiceImpl implements RedPackService {
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private RedPackDAO redPackDAO;
    @Autowired
    private RedPackTranscational redPackTranscational;
    @Autowired
    private EventPublisher eventPublisher;

    @Override
    public RedPacket createRedPack(RedPackRequest request) {

        //1.判断红包数量是否在边界范围内
        if (request.getRedPackCount() < RedPackConst.MIN_USER_COUNT || request.getRedPackCount() > RedPackConst.MAX_USER_COUNT) {
            throw new RedPackException("红包数量有问题!") ;
        }

        //2.判断限定红包金额下，每个人能否保证能拿到一份钱
        if (request.getAmount().intValue() < request.getRedPackCount() || request.getRedPackCount() > request.getAmount().intValue()/0.01) {
            throw new RedPackException("红包总金额太小，无法满足每个人拿到一分钱!") ;
        }

        //3.判断用户余额是否充足
        Account payAccount = accountDAO.select(request.getAccountId());
        if (payAccount.getAmount().compareTo(request.getAmount()) < 0) {
            throw new RedPackException("账户余额不足!") ;
        }

        //4.以上条件均满足，则尝试去创建红包
        RedPacket redPacket = tryCreate(request);

        //5.进行数据库操作
        payAccount.setAmount(payAccount.getAmount().subtract(request.getAmount()));
        redPackTranscational.saveOrUpdate(payAccount, redPacket);

        //6.异步通知到群里
        eventPublisher.publish(new RedPackEvent(redPacket, "发红包事件"));

        return redPacket;
    }

    @Override
    public BigDecimal grabRedPack(int accountId, String redPackId) {
        RedPacket redPacket = redPackDAO.select(redPackId);
        RedPacket.RedPacketItem[] items = redPacket.getRedPacketItemArr();

        //1.判断当前账户是否已领取红包
        int currentAccountId = accountId;
        for (int i = 0; i < items.length - redPacket.getLeftCount(); i++) {
            if (items[i].getAccountId() == currentAccountId) {
                throw new RedPackException("当前账户已领取红包!");
            }
        }

        //2.判断是否还有剩余红包
        if (redPacket.getLeftCount() < RedPackConst.MIN_REDPACK_COUNT) {
            throw new RedPackException("红包已领完!");
        }

        //3.从数组中取出一个红包发给用户
        RedPacket.RedPacketItem aRedPacketItem = items[items.length - redPacket.getLeftCount()];
        aRedPacketItem.setAccountId(currentAccountId);
        aRedPacketItem.setTimestamp(System.currentTimeMillis());
        redPacket.setLeftCount(redPacket.getLeftCount()-1);

        //4.通过版本号实现乐观锁，防止并发情况下红包数据已被修改
        int result = redPackDAO.update(redPacket, redPacket.getVersion());
        if (result == 0){
            //没有更新到数据，说明读到的数据已被修改导致版本号不一致
            throw new RedPackException("当前繁忙，请再试一次!");
        }

        return aRedPacketItem.getAmount();
    }

    @Override
    public boolean recoverRedPack(RedPacket redPacket) {
        //1.取出红包中没有被领取的红包item，并计算金额总和
        BigDecimal leftAmount = new BigDecimal(0);
        for (RedPacket.RedPacketItem item : redPacket.getRedPacketItemArr()){
            //领取账号为0 代表是默认值 未被领取
            if (item.getAccountId() == 0){
                leftAmount.add(item.getAmount());
            }
        }

        //2.查询具体账号信息
        Account account = accountDAO.select(redPacket.getAccountId());
        account.setAmount(account.getAmount().add(leftAmount));

        //3.更新账号金额信息
        int result = accountDAO.update(account);
        if (result != 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 尝试创建一个红包
     * @param request
     * @return
     */
    private RedPacket tryCreate(RedPackRequest request){
        //1.创建并初始化红包
        RedPacket redPacket = new RedPacket(request.getAccountId(), request.getRedPackCount(), request.getRemark(), request.getRedPackCount(), request.getGroupId());

        //2.计算每个红包item的金额
        List<BigDecimal> redPackAmountList = divideRedPack(request.getAmount(), request.getRedPackCount());
        RedPacket.RedPacketItem[] redPacketItemArr = redPacket.getRedPacketItemArr();

        //3.为每个item赋值
        for (int i = 0; i < redPackAmountList.size(); i++) {
            redPacketItemArr[i].setAmount(redPackAmountList.get(i));
        }

        return redPacket;
    }

    /**
     * 使用二倍均值法来计算红包
     * @param totalAmount 总金额
     * @param redPackCount 总红包数
     * @return
     */
    private List<BigDecimal> divideRedPack(BigDecimal totalAmount, int redPackCount){
        List<BigDecimal> amountList = new ArrayList<BigDecimal>();
        //1.元转换成分，人民币里分是最小单位
        int restAmount = totalAmount.intValue() * 100;
        int restRedPackNum = redPackCount;

        Random random = new Random();
        for (int i= 0; i< redPackCount- 1; i++){
            //随机范围：[1，剩余人均金额的两倍)，左闭右开
            int amount = random.nextInt(restAmount / restRedPackNum * 2 - 1) + 1;
            restAmount -= amount;
            restRedPackNum --;
            amountList.add(new BigDecimal(restAmount * 0.01));
        }

        amountList.add(new BigDecimal(restAmount * 0.01));
        return amountList;
    }


}
