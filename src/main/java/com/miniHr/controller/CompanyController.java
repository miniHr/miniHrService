package com.miniHr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miniHr.comm.RespCode;
import com.miniHr.comm.VariableKey;
import com.miniHr.entity.CompanyExt;
import com.miniHr.util.JsonUtil;
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

	@GetMapping(value = "/insert")
	public String addCompanyInfo(CompanyExt companyExt) {
		int resultKey = companyServiceImpl.insert(companyExt);
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put(VariableKey.RETCODE, RespCode.SUCCESS.getValue());

		Map<String, Object> retData = new HashMap<String,Object>();
		retData.put("keyID",resultKey);

		retMap.put(VariableKey.RETDATA,retData);
		return JsonUtil.toJson(retMap);
	}

	@GetMapping(value = "/query")
	public String queryCompanyInfo(String id){
		Company company = companyServiceImpl.selectCompanyInfo(Integer.parseInt(id));
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put(VariableKey.RETCODE, company == null ? RespCode.FAIL.getValue() : RespCode.SUCCESS.getValue());
		retMap.put(VariableKey.RETDATA, company);
		return JsonUtil.toJson(retMap);
	}
	
}
