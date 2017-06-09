package com.miniHr.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniHr.comm.BoothState;
import com.miniHr.comm.RespCode;
import com.miniHr.comm.UserLevel;
import com.miniHr.comm.VariableKey;
import com.miniHr.entity.Booth;
import com.miniHr.entity.Company;
import com.miniHr.entity.User;
import com.miniHr.pay.UnifiedorderPay;
import com.miniHr.pay.WechatSecurity;
import com.miniHr.service.BoothService;
import com.miniHr.service.CompanyService;
import com.miniHr.service.UserService;
import com.miniHr.util.StringUtil;

@RestController
public class BoothController {
    private static Logger log = LoggerFactory.getLogger(BoothController.class);

    @Autowired
    private BoothService boothService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/booth/query")
    public Map<String, Object> getAllBoothInfo() {
        log.info("查询展位信息");
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            result.put(VariableKey.RETDATA, boothService.getAllBooth());
            result.put(VariableKey.RETCODE, RespCode.SUCCESS.getValue());
        } catch (Exception e) {
            log.info("查询展位出现异常" + e);
            result.put(VariableKey.RETCODE, RespCode.FAIL.getValue());
        }
        return result;
    }

    /**
     * 1、检查该公司是否已经提交过该展位的订单，但是没有支付 2、如果是首次提交，则更新展位状态为购中
     */
    @RequestMapping("/booth/pay")
    public Map<String, Object> perchaseBooth(Integer boothId, String openId, String amount,
                                             Integer companyId) throws Exception {
        /* 响应结果 */
        Map<String, Object> result = new HashMap<String, Object>();

        if (StringUtil.isEmpty(openId) || StringUtil.isEmpty(boothId.toString())) {
            result.put(VariableKey.RETCODE, RespCode.FAIL.getValue());
            result.put(VariableKey.RETDATA, "购买参数为空");
            return result;
        }

        Booth booth = new Booth();
        booth.setId(boothId);
        booth.setCompanyId(companyId);
        booth.setState(BoothState.Saling.getState());
        int i = boothService.updateBoothInfoByOriState(booth, BoothState.Unsaled.getState()); // 根据状态和id更新展位
        if (i == 0) {
            result.put(VariableKey.RETCODE, RespCode.FAIL.getValue());
            result.put(VariableKey.RETDATA, "该展位被正在被其他公司购买");
            return result;
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("amount", amount);
        map.put("boothid", boothId.toString());
        map.put("openid", openId);
        log.info("构造支付报文" + map);

        result.put(VariableKey.RETDATA, UnifiedorderPay.WechatPay(map));
        result.put(VariableKey.RETCODE, RespCode.SUCCESS.getValue());
        return result;
    }

    @RequestMapping("/booth/payCompletly/{boothId}/{openId}")
    public void completePay(@PathVariable Integer boothId, @PathVariable String openId,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        InputStream in = request.getInputStream();
        OutputStream out = response.getOutputStream();
        String content = IOUtils.toString(in);
        log.info("接收到的通知内容：" + content);
        IOUtils.closeQuietly(in);

        if (!WechatSecurity.verify(StringUtil.deleteCdata(content), UnifiedorderPay.key)) {
            String failRes = "<xml><return_code><![CDATA[FAIL]]</return_code><return_msg><![CDATA[verify fail!]]</return_msg></xml>";
            out.write(failRes.getBytes());
        }

        /* 更新展位表 */
        Booth booth = boothService.getBoothById(boothId).get(0);
        booth.setState(BoothState.Saled.getState());
        int i = boothService.updateBoothInfo(booth);
        if (i == 1)
            log.info("更新展位表成功，展位id :" + booth.getId());

        /* 更新用户表，将用户更新为企业用户 */
        User user = new User();
        user.setOpenId(openId);
        user.setLevel(UserLevel.PAYED_COMPANY_USER.getLevel());
        i = userService.modifyUserLevelByOpenId(user);
        if (i == 1)
            log.info("更新用户级别成功");

        /* 更新企业表 将展位id写入企业表 */
        Company company = new Company();
        company.setBoothId(boothId);
        company.setId(booth.getCompanyId());
        i = companyService.modifyBoothIdOfCompanyById(company);
        if (i == 1)
            log.info("为公司添加展位成功");

        /* 收到通知 就认为是成功的 */
        out.write("<xml><return_code><![CDATA[SUCCESS]]</return_code><return_msg><![CDATA[ok]]</return_msg></xml>"
            .getBytes());
        in.close();
        out.close();
    }

    @RequestMapping("/booth/revert")
    public Map<String, Object> cancelPay(Integer boothId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(VariableKey.RETDATA, "展位退订成功");
        result.put(VariableKey.RETCODE, RespCode.SUCCESS.getValue());

        Booth booth = new Booth();
        booth.setId(boothId);
        int i = boothService.updateBoothInfo(booth);

        if (i == 0) {
            result.put(VariableKey.RETCODE, RespCode.FAIL.getValue());
            result.put(VariableKey.RETDATA, "展位退订失败，请联系客服");
        }
        return result;
    }
}
