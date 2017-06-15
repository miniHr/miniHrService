package com.miniHr.controller;

import com.miniHr.comm.RespCode;
import com.miniHr.comm.VariableKey;
import com.miniHr.entity.Company;
import com.miniHr.entity.CompanyExt;
import com.miniHr.entity.Job;
import com.miniHr.service.CompanyService;
import com.miniHr.service.JobService;
import com.miniHr.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/job")
public class JobController {
	private static Logger log = LoggerFactory.getLogger(JobController.class);

	@Autowired
	private CompanyService companyServiceImpl;

	@Autowired
	private JobService jobServiceImpl;

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

}
