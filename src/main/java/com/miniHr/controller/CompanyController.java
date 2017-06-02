package com.miniHr.controller;

import java.util.List;

import com.miniHr.entity.CompanyExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.miniHr.entity.Company;
import com.miniHr.service.CompanyService;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {
	private static Logger log = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	private CompanyService companyServiceImpl;
	
	@RequestMapping(value = "/query/{type}")
	public List<Company> queryJob(@PathVariable String type) {
		log.info("接收到应聘者请求！！");
		Company company = new Company();
//		company.setType(type);
		return companyServiceImpl.findByType(company);
	}

	@PostMapping(value = "/insert")
	public String addCompanyInfo(CompanyExt companyExt){
		boolean reuslt = companyServiceImpl.insert(companyExt);
		return reuslt == true ? "00" : "01";
	}

	@GetMapping(value = "/query/{id}")
	public Company queryCompanyInfo(@PathVariable int id){
		return companyServiceImpl.selectCompanyInfo(id);
	}
	
}
