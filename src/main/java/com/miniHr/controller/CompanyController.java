package com.miniHr.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniHr.entity.Company;
import com.miniHr.service.CompanyService;

@RestController
public class CompanyController {
	private static Logger log = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	private CompanyService companyServiceImpl;
	
	@RequestMapping(value = "/query/{type}")
	public List<Company> queryJob(@PathVariable String type) {
		log.info("接收到应聘者请求！！");
		Company company = new Company();
		company.setType(type);
		return companyServiceImpl.findByType(company);
	}
	
}
