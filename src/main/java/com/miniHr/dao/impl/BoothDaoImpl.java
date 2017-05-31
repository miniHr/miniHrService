package com.miniHr.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.miniHr.dao.BoothDao;
import com.miniHr.dao.CustomerRowMapper;
import com.miniHr.entity.Booth;
import com.miniHr.vo.BoothVo;

@Repository("boothDao")
public class BoothDaoImpl implements BoothDao {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Override
	public List<Booth> queryAllBooth() {
		String sql = "SELECT * FROM BOOTH_INFO";
		return template.query(sql, CustomerRowMapper.newInstance(Booth.class));
	}

	@Override
	public int updateBoothInfo(Booth booth) {
		String sql = "UPDATE BOOTH_INFO SET STATE=:state,COMPANY_ID=:companyId where ID=:id";
		return template.update(sql, new BeanPropertySqlParameterSource(booth));
	}

	@Override
	public Booth getBoothById(Integer id) {
		String sql = "SELECT * FROM BOOTH_INFO WHERE ID=:id";
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("id", id.toString());
		return template.queryForObject(sql, paramMap, CustomerRowMapper.newInstance(Booth.class));
	}
	public NamedParameterJdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(NamedParameterJdbcTemplate template) {
		this.template = template;
	}

	@Override
	public int updateBoothInfoWithState(BoothVo vo) {
		String sql = "UPDATE BOOTH_INFO SET STATE=:state,COMPANY_ID=:companyId,update_dt=current_timestamp where ID=:id AND STATE=:oriState";
		return template.update(sql, new BeanPropertySqlParameterSource(vo));
	}
	
}
