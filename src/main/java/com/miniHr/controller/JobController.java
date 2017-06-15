package com.miniHr.controller;

import com.miniHr.comm.RespCode;
import com.miniHr.comm.VariableKey;
import com.miniHr.entity.Company;
import com.miniHr.entity.CompanyExt;
import com.miniHr.entity.Job;
import com.miniHr.service.CompanyService;
import com.miniHr.service.JobService;
import com.miniHr.service.UserService;
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

	/**
	 * 职位推荐
	 *
	 * @param openId
	 * @return
     */
	@GetMapping(value = "/recommend")
	public String recommend(String openId) {
		log.info("recommend request param:{}", openId);
		List<Job> jobs = jobServiceImpl.recommendJobs(openId);
		Map<String,Object> retMap = new HashMap<>();
		retMap.put(VariableKey.RETCODE, RespCode.SUCCESS.getValue());

		Map<String, Object> retData = new HashMap<>();
		retData.put(VariableKey.LISTINFOS ,jobs);

		retMap.put(VariableKey.RETDATA,retData);
		String retStr = JsonUtil.toJson(retMap);
		log.info("recommend response param:{}", retStr);
		return retStr;
	}

	/**
	 * 职位查询
	 *
	 * @return
     */
	@GetMapping(value = "/query")
	public String queryJobInfo() {

		return null;
	}

}
