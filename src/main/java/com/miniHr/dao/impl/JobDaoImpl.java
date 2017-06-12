package com.miniHr.dao.impl;

import com.miniHr.dao.CustomerRowMapper;
import com.miniHr.dao.JobDao;
import com.miniHr.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
