package com.miniHr.service;

import java.util.List;

import com.miniHr.entity.Company;
import com.miniHr.entity.CompanyExt;

/**
 * 企业信息服务接口类
 */
public interface CompanyService {

    /**
     * 添加一个企业信息
     * @param companyExt
     * @return 新增记录的主键ID
     */
    int insert(CompanyExt companyExt);

    /**
     * 查询企业信息
     * @param id
     * @return
     */
    Company selectCompanyInfo(int id);
    
    /**
     * 为公司添加展位
     */
    int modifyBoothIdOfCompanyById(Company company);
}
