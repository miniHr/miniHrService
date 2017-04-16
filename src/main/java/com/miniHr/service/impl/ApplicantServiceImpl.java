package com.miniHr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.miniHr.service.ApplicantService;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(String type) {
        jdbcTemplate.update("update applicant set count=count+1 where type=?",Integer.parseInt(type));
    }

    @Override
    public List<String> statistics() {
        return jdbcTemplate.queryForList("select count from applicant group by type", String.class);
    }
}
