package com.miniHr.dao.impl;

import com.miniHr.dao.CustomerRowMapper;
import com.miniHr.dao.ResumeDao;
import com.miniHr.entity.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * Created by DELL on 2017/6/12.
 */
public class ResumeDaoImpl implements ResumeDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 通过ID查询简历授权信息
     *
     * @param resume
     * @return
     */
    @Override
    public Resume selectById(Resume resume) {
        String sql = "SELECT * FROM RESUME WHERE id=:id";
        return namedParameterJdbcTemplate.queryForObject(sql, new BeanPropertySqlParameterSource(resume), CustomerRowMapper.newInstance(Resume.class));
    }

    /**
     * 新增家里授权
     *
     * @param resume
     * @return
     */
    @Override
    public int insert(Resume resume) {
        String sql = "INSERT INTO RESUME (OPENID, JOB_ID, COMPANY_ID, STATE, CREATE_DT, CREATER, UPDATE_DT, UPDATER) " +
                "VALUES (:openid, :jobId, :companyId, :state, 'sysdate()', 'system', 'sysdate()', 'system')";
        KeyHolder holder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(resume), holder);
        return holder.getKey().intValue();
    }
}
