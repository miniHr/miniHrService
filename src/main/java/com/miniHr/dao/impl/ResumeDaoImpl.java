package com.miniHr.dao.impl;

import com.miniHr.dao.CustomerRowMapper;
import com.miniHr.dao.ResumeDao;
import com.miniHr.entity.Resume;
import com.miniHr.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by DELL on 2017/6/12.
 */
@Repository
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
                "VALUES (:openid, :jobId, :companyId, :state, sysdate(), 'system', sysdate(), 'system')";
        KeyHolder holder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(resume), holder);
        return holder.getKey().intValue();
    }

    /**
     * 企业用户获取所在公司收到的所有简历信息
     *
     * @param resume
     * @return
     */
    @Override
    public List<User> selectByCompanyUser(Resume resume) {
        String sql = "select u.* from USER_INFO u where OPEN_ID IN (select r.OPENID from RESUME r where r.COMPANY_ID=:companyId and r.state=:state AND r.OPENID !=:openid)";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertySqlParameterSource(resume), CustomerRowMapper.newInstance(User.class));
    }
}
