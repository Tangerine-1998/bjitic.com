package com.bjitic.p2p.service.loan;

import com.bjitic.p2p.model.loan.BidInfo;

import java.util.List;
import java.util.Map;

/**
 * Author:橘子🍊
 * 2020/3/17
 */
public interface BidInfoService {

    /**
     * 获取平台累计投资金额
     * @return
     */
    Double queryAllBidMoney();

}
