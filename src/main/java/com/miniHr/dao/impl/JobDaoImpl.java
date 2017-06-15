package com.miniHr.dao.impl;

import com.miniHr.dao.CustomerRowMapper;
import com.miniHr.dao.JobDao;
import com.miniHr.entity.Company;
import com.miniHr.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Job Dao层实现类
 *
 * Created by DELL on 2017/6/12.
 */
@Repository
public class JobDaoImpl implements JobDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /**
     * 通过ID查询job信息
     *
     * @param job
     * @return
     */
    @Override
    public Job selectById(Job job) {
        String sql = "SELECT * FROM JOB WHERE id=:id";
        return namedParameterJdbcTemplate.queryForObject(sql, new BeanPropertySqlParameterSource(job), CustomerRowMapper.newInstance(Job.class));
    }

    /**
     * 通过公司信息查询职位信息
     *
     * @param company
     * @return
     */
    @Override
    public List<Job> selectJobByCompanyInfo(Company company) {
        String sql = "select * from JOB where COMPANY_ID=:id";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertySqlParameterSource(company), CustomerRowMapper.newInstance(Job.class));
    }
}
