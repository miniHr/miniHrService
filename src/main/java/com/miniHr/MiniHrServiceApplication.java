package com.miniHr;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniHr.entity.Company;
import com.miniHr.service.impl.ApplicantServiceImpl;
import com.miniHr.service.impl.CompanyServiceImpl;

@SpringBootApplication
@RestController
public class MiniHrServiceApplication {

	private static Logger log = LoggerFactory.getLogger(MiniHrServiceApplication.class);

	@Autowired
	private CompanyServiceImpl companyServiceImpl;

	@Autowired
	private ApplicantServiceImpl applicantServiceImpl;

	@RequestMapping(value = "/query/{type}")
	public List<Company> queryJob(@PathVariable String type) {
		log.info("接收到应聘者请求！！");
		Company company = new Company();
		company.setType(type);
		return companyServiceImpl.findByType(company);
	}

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

	public static void main(String[] args) {
		SpringApplication.run(MiniHrServiceApplication.class, args);
	}
}
