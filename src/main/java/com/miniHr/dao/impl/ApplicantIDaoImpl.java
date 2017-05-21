package com.miniHr.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.miniHr.dao.ApplicantDao;

@Repository
public class ApplicantIDaoImpl implements ApplicantDao {
	@Autowired
	private NamedParameterJdbcTemplate template;

	@SuppressWarnings("unchecked")
	@Override
	public void incrCountByType(String type) {
		String sql = "update applicant set count=count+1 where type=:type";
		Map<String,String> params = new HashMap<String,String>();
		params.put("type", type);
		template.execute(sql, params, new PreparedStatementCallback(){

			@Override
			public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				return null;
			}
			
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getCountByType(String type) {
		String sql = "select count from applicant group by :type";
		Map<String,String> params = new HashMap<String,String>();
		params.put("type", type);
		Object o = template.execute(sql, params, new PreparedStatementCallback(){

			@Override
			public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					System.out.println(rs.getObject(1));;
				}
				return rs.getObject(1);
			}
		});
		System.out.println("================" + o);
		return 0;
	}

}
