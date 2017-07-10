package com.miniHr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miniHr.comm.RespCode;
import com.miniHr.comm.VariableKey;
import com.miniHr.entity.CompanyExt;
import com.miniHr.entity.Job;
import com.miniHr.service.JobService;
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

	@Autowired
	private JobService jobServiceImpl;

	@GetMapping(value = "/insert")
	public String addCompanyInfo(CompanyExt companyExt) {
		log.info("addCompanyInfo request param:{}", companyExt);
		int resultKey = companyServiceImpl.insert(companyExt);
		Map<String, Object> retMap = new HashMap<>();
		retMap.put(VariableKey.RETCODE, RespCode.SUCCESS.getValue());

		Map<String, Object> retData = new HashMap<>();
		retData.put("keyID", resultKey);

		retMap.put(VariableKey.RETDATA, retData);
		return JsonUtil.toJson(retMap);
	}

	@GetMapping(value = "/query")
	public String queryCompanyInfo(String id) {
		Company company = companyServiceImpl.selectCompanyInfo(Integer.parseInt(id));

		List<Job> jobs = jobServiceImpl.findByCompanyId(company);

		Map<String, Object> retMap = new HashMap<>();
		retMap.put(VariableKey.RETCODE, company == null ? RespCode.FAIL.getValue() : RespCode.SUCCESS.getValue());
		retMap.put(VariableKey.RETDATA, company);
		retMap.put(VariableKey.RETDATA_TWO, jobs);
		return JsonUtil.toJson(retMap);
	}

	@GetMapping(value = "/query/limit")
	public String queryCompanyInfoLimited(String limit) {
		return JsonUtil.toJson(companyServiceImpl.findCompanyLimited(limit));
	}
}
