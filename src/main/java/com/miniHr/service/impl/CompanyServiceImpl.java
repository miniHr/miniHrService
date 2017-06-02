package com.miniHr.service.impl;

import java.util.List;

import com.miniHr.dao.UserDao;
import com.miniHr.entity.CompanyExt;
import com.miniHr.entity.User;
import com.miniHr.entity.UserLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.miniHr.dao.CompanyDao;
import com.miniHr.entity.Company;
import com.miniHr.service.CompanyService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 企业信息服务实现类
 */
@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	@Qualifier("companyDao")
	private CompanyDao companyDaoImpl;

	@Autowired
	@Qualifier("userDao")
	private UserDao userdaoImpl;

	@Override
	public void create(String name, String job, String position, String type) {
		Company company = new Company();
//		company.setJob(job);
//		company.setName(name);
//		company.setPosition(Integer.parseInt(position));
//		company.setType(type);
		companyDaoImpl.addCompany(company);
	}

	@Override
	public void deleteById(Integer id) {
		
	}

	@Override
	public List<Company> findByType(Company company) {
//		return companyDaoImpl.findByType(company.getType());
		return null;
	}

	/**
	 * 添加一个企业信息
	 * @param companyExt
	 * @return
     */
	@Override
	@Transactional
	public boolean insert(CompanyExt companyExt) {
		User user = new User();
		user.setOpenId(companyExt.getOpenId());
		user.setLevel(UserLevel.COMPANY.getLevel());
		userdaoImpl.addUser(user);

		return companyDaoImpl.addCompany(companyExt) > 0;
	}

	/**
	 * 查询企业信息
	 * @param id
	 * @return
     */
	@Override
	public Company selectCompanyInfo(int id) {
		return companyDaoImpl.selectCompanyInfoById(id);
	}
}
