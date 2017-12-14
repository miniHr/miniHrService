package com.miniHr.controller;

import java.util.HashMap;
import java.util.Map;

import com.miniHr.comm.RespCode;
import com.miniHr.comm.VariableKey;
import com.miniHr.dao.CompanyDao;
import com.miniHr.entity.Booth;
import com.miniHr.entity.CompanyExt;
import com.miniHr.service.CompanyService;
import com.miniHr.service.impl.CompanyServiceImpl;
import com.miniHr.util.StringUtil;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miniHr.service.BoothService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin")
public class ZhiChengController {

    @Autowired
    private BoothService boothService;

    @Autowired
    private CompanyDao companyDao;

    /**
     * @param pageNum 传入的页数
     * @param res
     * @return
     */
    @RequestMapping(value = "/boothInfo")
    public String adminBooth(@Nullable Integer pageNum, @Nullable Integer count, Map<String, Object> res) {
        if (null == count) {
            count = boothService.getAllBoothInfoCount();
        }
        int pageCondition = 0;
        if (null != pageNum) {
            pageCondition = (pageNum - 1) * 30;
        } else {
            pageNum = 1;
        }
        res.put("boothInfos", boothService.getAllBoothWithCompanyNameByPage(pageCondition));
        res.put("dataCount", count);
        res.put("currentPage", pageNum);
        return "boothInfo";
    }

    @RequestMapping(value = "/occupy", method = RequestMethod.GET)
    public String occupyBooth(String id, Map<String, Object> res) {
        res.put("id", id);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String occupyBooth(HttpServletRequest request, Map<String, Object> res) {
        String companyName = request.getParameter("companyName");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String boothId = request.getParameter("boothId");
        if (StringUtil.isEmpty(companyName) || StringUtil.isEmpty(name) || StringUtil.isEmpty(phone)) {
            res.put(VariableKey.RETDATA, "输入有误");
            return "error";
        } else {
            CompanyExt ext = new CompanyExt();
            ext.setCompanyName(companyName);
            ext.setName(name);
            ext.setPhone(phone);
            ext.setBoothId(Integer.parseInt(boothId));
            ext.setAuthCode(String.valueOf(System.currentTimeMillis()).substring(7));
            int id = companyDao.addCompany(ext);

            Booth b = new Booth();
            b.setId(Integer.parseInt(boothId));
            b.setCompanyId(id);
            b.setState("3");
            boothService.updateBoothInfo(b);
            return "redirect:/admin/boothInfo";
        }
    }

    @RequestMapping(value = "/revert", method = RequestMethod.GET)
    public String revertBooth(String id, String companyId, Map<String, Object> res) {
        Booth b = new Booth();
        b.setId(Integer.parseInt(id));
        b.setState("1");
        b.setCompanyId(null);
        boothService.updateBoothInfo(b);

        companyDao.deleteById(Integer.parseInt(companyId));
        res.put(VariableKey.RETCODE, RespCode.SUCCESS.getValue());
        return "redirect:/admin/boothInfo";
    }
}
