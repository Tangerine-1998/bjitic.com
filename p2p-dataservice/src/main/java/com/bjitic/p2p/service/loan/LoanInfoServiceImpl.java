package com.bjitic.p2p.service.loan;

import com.bjitic.p2p.common.constant.Constants;
import com.bjitic.p2p.mapper.loan.LoanInfoMapper;
import com.bjitic.p2p.model.loan.LoanInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/**
 * Author:橘子🍊
 * 2020/3/17
 */

@Service
public class LoanInfoServiceImpl implements LoanInfoService {

    @Autowired
    private LoanInfoMapper loanInfoMapper;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public Double queryHistoryAverageYearRate() {

        redisTemplate.setKeySerializer(new StringRedisSerializer());

        Double historyAverageYearRate = (Double) redisTemplate.opsForValue().get(Constants.HISTORY_AVERAGE_YEAR_RATE);

        if (!ObjectUtils.allNotNull(historyAverageYearRate)) {

            synchronized (this) {

                historyAverageYearRate = (Double) redisTemplate.opsForValue().get(Constants.HISTORY_AVERAGE_YEAR_RATE);

                if (!ObjectUtils.allNotNull(historyAverageYearRate)) {



                    historyAverageYearRate = loanInfoMapper.selectHistoryAverageYearRate();

                    redisTemplate.opsForValue().set(Constants.HISTORY_AVERAGE_YEAR_RATE, historyAverageYearRate, 15, TimeUnit.MINUTES);
                }

            }


        }

        return historyAverageYearRate;
    }

    @Override
    public List<LoanInfo> queryLoanInfoListByProductType(Map<String, Object> paramMap) {
        return loanInfoMapper.selectLoanInfoListByProductType(paramMap);
    }
}
