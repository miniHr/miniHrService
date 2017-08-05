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
import com.miniHr.entity.BoothExt;
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
    public List<Booth> getBoothById(Integer id) {
        String sql = "SELECT * FROM BOOTH_INFO WHERE ID=:id";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("id", id.toString());
        return template.query(sql, paramMap, CustomerRowMapper.newInstance(Booth.class));
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

    @Override
    public List<Booth> getBoothInfoByStateAndId(Booth booth) {
        String sql = "SELECT * FROM BOOTH_INFO WHERE STATE=:state AND COMPANY_ID=:companyId AND ID=:Id";
        return template.query(sql, new BeanPropertySqlParameterSource(booth),
            CustomerRowMapper.newInstance(Booth.class));
    }

    @Override
    public List<BoothExt> getAllBoothWithCompanyName() {
        String sql = "SELECT b.*,c.COMPANY_NAME FROM BOOTH_INFO b LEFT JOIN COMPANY_INFO c ON b.COMPANY_ID=c.ID";
        return template.query(sql, CustomerRowMapper.newInstance(BoothExt.class));
    }
}
