package com.miniHr.dao;

import java.util.List;

import com.miniHr.entity.Company;
import com.miniHr.entity.User;
import com.miniHr.entity.UserExt;

public interface CompanyDao {
	/**
	 * 新增企业
	 */
	int addCompany(Company company);
	
	/**
	 * 根据类型查找企业
	 */
	List<Company> findByType(String type);
	
	/**
	 * 根据企业id删除企业
	 */
	
	void deleteById(int id);

	/**
	 * 根据id查询企业信息
	 * @param id
     * @return
     */
	Company selectCompanyInfoById(int id);
	
	/**根据公司Id更新公司表中的展位信息*/
	int updateBoothInfoOfCompanyById(Company company);
	
    /**
     * 分页查询公司
     * @param limit
     * @return
     */
    List<Company> findCompanyLimited(String limit);

	/**
	 * 通过登录码查找公司
	 * @param company
	 * @return
	 */
	Company findCompanyByAuthCode(Company company);

	/**
	 * 根据企业方查找全简历
	 * @param company
	 * @return
	 */
	List<UserExt> findUserByResume(Company company);
}
