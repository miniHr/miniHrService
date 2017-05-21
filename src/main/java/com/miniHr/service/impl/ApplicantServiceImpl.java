package com.miniHr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniHr.dao.ApplicantDao;
import com.miniHr.service.ApplicantService;

@Service
public class ApplicantServiceImpl implements ApplicantService {
	@Autowired
	private ApplicantDao applicantDaoImpl;
	
    @Override
    public void insert(String type) {
    	applicantDaoImpl.incrCountByType(type);
    }

    @Override
    public List<String> statistics() {
    	applicantDaoImpl.getCountByType("1");
        return new ArrayList<String>();
    }
}
