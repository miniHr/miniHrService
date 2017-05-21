package com.miniHr.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.miniHr.dao.CompanyDao;
import com.miniHr.entity.Company;


@Repository("companyDao")
public class CompanyDaoImpl implements CompanyDao {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Override
	public void addCompany(Company company) {
		String sql = "insert into company(name, job,position,type) values(:name,:job,:position,:type)";
		template.update(sql, new BeanPropertySqlParameterSource(company));
	}

	@Override
	public List<Company> findByType(String type) {
		String sql = "select * from company where type=:type";
		
		Company company = new Company();
		company.setType(type);
		return template.queryForList(sql, new BeanPropertySqlParameterSource(company), Company.class);
	}

	@Override
	public void deleteById(int id) {
		String sql = "delete from company where id=:id";
		Company company = new Company();
		company.setId(id);
		template.update(sql, new BeanPropertySqlParameterSource(company));
	}

}
