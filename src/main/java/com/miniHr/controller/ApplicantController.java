package com.miniHr.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniHr.service.ApplicantService;

@RestController
public class ApplicantController {
	private static Logger log = LoggerFactory.getLogger(ApplicantController.class);
	
	@Autowired
	private ApplicantService applicantServiceImpl;
	
	@RequestMapping("/count")
	public List<String> countJob() {
		log.info("接收到企业者请求！！");
		List<String> list = applicantServiceImpl.statistics();
		return list;
	}

	@RequestMapping("/insert/{type}")
	public String insert(@PathVariable String type) {
		log.info("有一个人对" + type + "感兴趣");
		applicantServiceImpl.insert(type);
		return "Success";
	}
}
