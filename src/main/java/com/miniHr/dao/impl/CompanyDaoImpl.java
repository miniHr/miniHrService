package com.miniHr.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.miniHr.dao.CompanyDao;
import com.miniHr.entity.Company;


@Repository("companyDao")
public class CompanyDaoImpl implements CompanyDao {
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 新增企业信息
	 *
	 * @param company
	 * @return 新增记录的主键ID
     */
	@Override
	public int addCompany(Company company) {
//		String sql = "insert into company(name, job,position,type) values(:name,:job,:position,:type)";
		String sql = "insert into COMPANY_INFO(COMPANY_NAME, IMAGE,SCALE,ADDRESS,WELFARE,NAME,PHONE,BOOTH_ID,CREATE_DT,CREATER,UPDATE_DT,UPDATER) values(:companyName,:image,:scale,:address,:welfare,:name,:phone,:boothId,sysdate(),'creater',sysdate(),'updater')";
		KeyHolder holder = new GeneratedKeyHolder();
		template.update(sql, new BeanPropertySqlParameterSource(company), holder);
		return holder.getKey().intValue();
	}

	@Override
	public List<Company> findByType(String type) {
		String sql = "select * from company where type=:type";
		
		Company company = new Company();
//		company.setType(type);
		return template.queryForList(sql, new BeanPropertySqlParameterSource(company), Company.class);
	}

	@Override
	public void deleteById(int id) {
		String sql = "delete from company where id=:id";
		Company company = new Company();
		company.setId(id);
		template.update(sql, new BeanPropertySqlParameterSource(company));
	}

	/**
	 * 根据id查询企业信息
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Company selectCompanyInfoById(int id) {
		String sql = "select * from company where id=:id";
		Company company = new Company();
		company.setId(id);
		return template.queryForList(sql, new BeanPropertySqlParameterSource(company), Company.class).get(0);
	}

	@Override
	public int updateBoothInfoOfCompanyById(Company company) {
		String sql = "update company_info set booth_id=:boothId where id=:id";
		return template.update(sql, new BeanPropertySqlParameterSource(company));
	}

}
