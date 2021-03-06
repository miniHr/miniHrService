package com.miniHr.dao.impl;

import java.util.List;

import com.miniHr.entity.User;
import com.miniHr.entity.UserExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.miniHr.dao.CompanyDao;
import com.miniHr.dao.CustomerRowMapper;
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
        // String sql = "insert into company(name, job,position,type)
        // values(:name,:job,:position,:type)";
        String sql = "insert into COMPANY_INFO(COMPANY_NAME, IMAGE,SCALE,ADDRESS,WELFARE,NAME,PHONE,BOOTH_ID,CREATE_DT,CREATER,UPDATE_DT,UPDATER,AUTH_CODE) values(:companyName,:image,:scale,:address,:welfare,:name,:phone,:boothId,sysdate(),'creater',sysdate(),'updater',:authCode)";
        KeyHolder holder = new GeneratedKeyHolder();
        template.update(sql, new BeanPropertySqlParameterSource(company), holder);
        return holder.getKey().intValue();
    }

    @Override
    public List<Company> findByType(String type) {
        String sql = "select * from company where type=:type";

        Company company = new Company();
        // company.setType(type);
        return template.queryForList(sql, new BeanPropertySqlParameterSource(company), Company.class);
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from COMPANY_INFO where id=:id";
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
        String sql = "SELECT * FROM COMPANY_INFO WHERE ID=:id";
        Company company = new Company();
        company.setId(id);
        return template.queryForObject(sql, new BeanPropertySqlParameterSource(company),
                CustomerRowMapper.newInstance(Company.class));
    }

    @Override
    public int updateBoothInfoOfCompanyById(Company company) {
        String sql = "UPDATE COMPANY_INFO SET BOOTH_ID=:boothId WHERE ID=:id";
        return template.update(sql, new BeanPropertySqlParameterSource(company));
    }

    @Override
    public List<Company> findCompanyLimited(String limit) {
        String sql = "SELECT ID,COMPANY_NAME,IMAGE,SCALE,ADDRESS,WELFARE,NAME,PHONE,INFORMATION,BOOTH_ID FROM COMPANY_INFO ORDER BY ID LIMIT "
                + limit + ",8";
        return template.query(sql, CustomerRowMapper.newInstance(Company.class));
    }

    @Override
    public Company findCompanyByAuthCode(Company company) {
        String sql = "SELECT ID,COMPANY_NAME FROM COMPANY_INFO WHERE AUTH_CODE=:authCode";
        List<Company> list = template.query(sql, new BeanPropertySqlParameterSource(company), CustomerRowMapper.newInstance(Company.class));
        if (list.size() < 1) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<UserExt> findUserByResume(Company company) {
        String sql="SELECT u.*,j.JOB_NAME FROM RESUME r LEFT JOIN USER_INFO u ON r.OPENID = u.OPEN_ID LEFT JOIN COMPANY_INFO c ON r.company_id = c.id LEFT JOIN JOB j ON r.job_id = j.id WHERE u.LEVEL=1 AND c.ID=:id";
        return template.query(sql,new BeanPropertySqlParameterSource(company),CustomerRowMapper.newInstance(UserExt.class));
    }
}
