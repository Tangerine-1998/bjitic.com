package com.bjitic.p2p.service.loan;

import com.bjitic.p2p.model.loan.LoanInfo;

import java.util.List;
import java.util.Map;

/**
 * Author:橘子🍊
 * 2020/3/17
 */
public interface LoanInfoService {

    /**
     * 获取历史平均年化收益率
     * @return Double数据
     */
    Double queryHistoryAverageYearRate();

    /**
     * 根据产品类型获取产品信息列表
     * @param paramMap
     * @return
     */
    List<LoanInfo> queryLoanInfoListByProductType(Map<String, Object> paramMap);
}
