package com.miniHr.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.miniHr.entity.Company;
import com.miniHr.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void create(String name, String job, String position, String type) {
		jdbcTemplate.update("insert into company(name, job,position,type) values(?,?,?,?)", name, job, position, type);
	}

	@Override
	public void deleteById(Integer id) {
		jdbcTemplate.update("delete from USER where id = ?", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> findByType(Company company) {
		return (List<Company>) jdbcTemplate.query("select * from company where type = ? order by position",
				new Object[] { company.getType() }, new int[] { java.sql.Types.VARCHAR }, new RowMapper() {
					@Override
					public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
						Company com  = new Company();
						com.setId(arg0.getInt("id"));
						com.setName(arg0.getString("name"));
						com.setJob(arg0.getString("job"));
						com.setPosition(arg0.getInt("position"));
						com.setType(arg0.getString("type"));
						com.setImage(arg0.getString("image"));
						return com;
					}
				});
	}
}
