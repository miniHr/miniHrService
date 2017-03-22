package com.miniHr.service;

import java.util.List;

import com.miniHr.entity.Company;

public interface CompanyService {

	/**
     * 新增一个职位
     * @param name
     * @param age
     */
    void create(String name,String job,String position,String type);
    
    /**
     * 根据id删除一个职位
     * @param name
     */
    void deleteById	(Integer id);
    
    /**
     * 根据type查询职位
     * @param type
     * @return
     */
    List<Company> findByType(Company company);
}
