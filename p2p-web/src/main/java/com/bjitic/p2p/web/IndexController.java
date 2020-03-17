package com.bjitic.p2p.web;

import com.bjitic.p2p.common.constant.Constants;
import com.bjitic.p2p.model.loan.LoanInfo;
import com.bjitic.p2p.service.loan.BidInfoService;
import com.bjitic.p2p.service.loan.LoanInfoService;
import com.bjitic.p2p.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Author:橘子🍊
 * 2020/3/17
 */
@Controller
public class IndexController{

    @Autowired
    private LoanInfoService loanInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private BidInfoService bidInfoService;

    @RequestMapping(value = "/index")
    public String index(Model model) {


        Double historyAverageYearRate = loanInfoService.queryHistoryAverageYearRate();
        model.addAttribute(Constants.HISTORY_AVERAGE_YEAR_RATE,historyAverageYearRate);


        Long allUserCount = userService.queryAllUserCount();
        model.addAttribute(Constants.ALL_USER_COUNT,allUserCount);


        Double allBidMoney = bidInfoService.queryAllBidMoney();
        model.addAttribute(Constants.ALL_BID_MONEY,allBidMoney);

        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("currentPage",0);


        paramMap.put("pageSize",1);
        paramMap.put("productType",Constants.PRODUCT_TYPE_X);
        List<LoanInfo> xLoanInfoList = loanInfoService.queryLoanInfoListByProductType(paramMap);
        model.addAttribute("xLoanInfoList",xLoanInfoList);



        paramMap.put("pageSize",4);
        paramMap.put("productType",Constants.PRODUCT_TYPE_U);
        List<LoanInfo> uLoanInfoList = loanInfoService.queryLoanInfoListByProductType(paramMap);
        model.addAttribute("uLoanInfoList",uLoanInfoList);

        paramMap.put("pageSize",8);
        paramMap.put("productType",Constants.PRODUCT_TYPE_S);
        List<LoanInfo> sLoanInfoList = loanInfoService.queryLoanInfoListByProductType(paramMap);
        model.addAttribute("sLoanInfoList",sLoanInfoList);


        return "index";
    }
}
