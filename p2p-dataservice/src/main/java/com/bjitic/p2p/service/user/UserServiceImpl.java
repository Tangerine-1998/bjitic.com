package com.bjitic.p2p.service.user;

import com.bjitic.p2p.common.constant.Constants;
import com.bjitic.p2p.mapper.user.FinanceAccountMapper;
import com.bjitic.p2p.mapper.user.UserMapper;
import com.bjitic.p2p.model.user.FinanceAccount;
import com.bjitic.p2p.model.user.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 * Author:橘子🍊
 * 2020/3/17
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FinanceAccountMapper financeAccountMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Long queryAllUserCount() {
        Long allUserCount = (Long) redisTemplate.opsForValue().get(Constants.ALL_USER_COUNT);


        if (!ObjectUtils.allNotNull(allUserCount)) {


            synchronized (this) {


                allUserCount = (Long) redisTemplate.opsForValue().get(Constants.ALL_USER_COUNT);


                if (!ObjectUtils.allNotNull(allUserCount)) {


                    allUserCount = userMapper.selectAllUserCount();


                    redisTemplate.opsForValue().set(Constants.ALL_USER_COUNT, allUserCount, 15, TimeUnit.MINUTES);

                }

            }


        }

        return allUserCount;
    }
}
