package com.miniHr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.miniHr.dao.CompanyDao;
import com.miniHr.entity.Company;
import com.miniHr.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	@Qualifier("companyDao")
	private CompanyDao companyDaoImpl;
	@Override
	public void create(String name, String job, String position, String type) {
		Company company = new Company();
		company.setJob(job);
		company.setName(name);
		company.setPosition(Integer.parseInt(position));
		company.setType(type);
		companyDaoImpl.addCompany(company);
	}

	@Override
	public void deleteById(Integer id) {
		
	}

	@Override
	public List<Company> findByType(Company company) {
		return companyDaoImpl.findByType(company.getType());
	}
}
