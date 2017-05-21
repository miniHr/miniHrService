package com.miniHr.dao;

import java.util.List;

import com.miniHr.entity.Company;

public interface CompanyDao {
	/**
	 * 新增企业
	 */
	void addCompany(Company company);
	
	/**
	 * 根据类型查找企业
	 */
	List<Company> findByType(String type);
	
	/**
	 * 根据企业id删除企业
	 */
	
	void deleteById(int id);
}
