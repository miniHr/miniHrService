package com.miniHr.controller;

import com.miniHr.comm.VariableKey;
import com.miniHr.entity.Company;
import com.miniHr.entity.User;
import com.miniHr.service.CompanyService;
import com.miniHr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/resumeInfo")
public class ResumeQueryController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    /**
     * 全简历(企业方)
     *
     * @param authCode
     * @param res
     * @return
     */
    @RequestMapping(value = "/allWithoutPhone")
    public String queryAllResumeWithoutPhone(String authCode, Map<String, Object> res) {
        Company c = loginCheck(authCode);
        if (null == c) {
            res.put(VariableKey.RETDATA, "没有找到相应企业信息！");
            return "error";
        } else {
            res.put("resumes", userService.findAllUser());
            return "AllResumesCompany";
        }
    }

    /**
     * 全简历(智诚方)
     *
     * @param res
     * @return
     */
    @RequestMapping(value = "/queryAll")
    public String queryAllResumeWithPhone(Map<String, Object> res) {
        res.put("resumes", userService.findAllUser());
        return "AllResumesZhiCheng";
    }

    /**
     * 通过登录码查找投递该公司的简历
     *
     * @param authCode
     * @param res
     * @return
     */
    @RequestMapping(value = "/resumeOfCompany")
    public String queryResumesWithAuthCode(String authCode, Map<String, Object> res) {
        Company c = loginCheck(authCode);
        if (null == c) {
            res.put(VariableKey.RETDATA, "没有找到相应企业信息！");
            return "error";
        } else {
            res.put("resumes", companyService.findUserByResume(c));
            res.put("authCode",authCode);
            return "ResumesOfCompany";
        }
    }

    public Company loginCheck(String authCode) {
        Company c = new Company();
        c.setAuthCode(authCode);
        return companyService.findCompanyByAuthCode(c);
    }
}
