package com.alipay.redpack.api.dao;

import com.alipay.redpack.api.entity.RedPacket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 红包数据库接入层
 * @Date 2019-04-10
 **/
@Mapper
public interface RedPackDAO {

    /**
     * 创建红包
     * @param redPacket
     * @return
     */
    int save(RedPacket redPacket);

    /**
     * 更新红包.
     * @param redPacket
     * @param version
     * @return
     */
    int update(RedPacket redPacket, int version);

    /**
     * 根据红包id获取红包
     * @param id 红包id
     * @return
     */
    RedPacket select(@Param(value = "id") String id);


    /**
     * 查询过期的红包
     * @param expireTime
     * @return
     */
    List<RedPacket> selectExpireRedPacket(@Param(value = "expireTime") long expireTime);
}
