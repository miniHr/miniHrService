package com.miniHr.service;

import java.util.List;

import com.miniHr.entity.Company;
import com.miniHr.entity.CompanyExt;

/**
 * 企业信息服务接口类
 */
public interface CompanyService {

    /**
     * 新增一个职位
     * @param name
     * @param job
     * @param position
     * @param type
     */
    void create(String name,String job,String position,String type);
    
    /**
     * 根据id删除一个职位
     * @param id
     */
    void deleteById	(Integer id);
    
    /**
     * 根据type查询职位
     * @param company
     * @return
     */
    List<Company> findByType(Company company);

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
}
