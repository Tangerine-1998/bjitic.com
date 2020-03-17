package com.bjitic.p2p.service.loan;

import com.bjitic.p2p.common.constant.Constants;
import com.bjitic.p2p.mapper.loan.BidInfoMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;
/**
 * Author:橘子🍊
 * 2020/3/17
 */

@Service
public class BidInfoServiceImpl implements BidInfoService {

    @Autowired
    private BidInfoMapper bidInfoMapper;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public Double queryAllBidMoney() {

        Double allBidMoney = (Double) redisTemplate.opsForValue().get(Constants.ALL_BID_MONEY);

        if (!ObjectUtils.allNotNull(allBidMoney)) {

            synchronized (this) {

                allBidMoney = (Double) redisTemplate.opsForValue().get(Constants.ALL_BID_MONEY);

                if (!ObjectUtils.allNotNull(allBidMoney)) {

                    allBidMoney = bidInfoMapper.selectAllBidMoney();

                    redisTemplate.opsForValue().set(Constants.ALL_BID_MONEY,allBidMoney,15, TimeUnit.SECONDS);

                }

            }

        }

        return allBidMoney;
    }
}