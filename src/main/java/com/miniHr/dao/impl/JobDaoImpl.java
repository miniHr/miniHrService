package com.miniHr.dao.impl;

import com.miniHr.dao.CustomerRowMapper;
import com.miniHr.dao.JobDao;
import com.miniHr.entity.Company;
import com.miniHr.entity.Job;
import com.miniHr.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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

    /**
     * 查询职位信息通过用户信息<br/>
     * （职位推荐）
     *
     * @param user
     * @return
     */
    @Override
    public List<Job> selectJobByUserInfo(User user) {
        StringBuffer sql = new StringBuffer("select * from JOB where 1=1 ");

        /**年龄*/
        if (!StringUtils.isEmpty(user.getAge())) {
            sql.append("and MINAGE <=:age and MAXAGE >=:age ");
        }
        /**性别*/
        if(!StringUtils.isEmpty(user.getSex())) {
            sql.append("and GENDER=:sex ");
        }
        /**学历*/
        if(!StringUtils.isEmpty(user.getEducation())) {
            sql.append("and EDUCATION=:education ");
        }
        /**行业*/
        if (!StringUtils.isEmpty(user.getIndustry()) && user.getIndustry().indexOf("应届") < 0 ) {
            sql.append("and INDUSTRY like %:industry% ");
        }

        /**只能十条记录*/
        sql.append("limit 0,10 ");

        return namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertySqlParameterSource(user), CustomerRowMapper.newInstance(Job.class));
    }
}
