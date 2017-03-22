package com.miniHr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.miniHr.entity.Company;
import com.miniHr.service.impl.CompanyServiceImpl;

@SpringBootApplication
@RestController
public class MiniHrServiceApplication {
	
	@Autowired
	private CompanyServiceImpl companyServiceImpl;
	
	@RequestMapping(value="/query/{type}")
	public List<Company> queryJob(@PathVariable String type){
		Company company=new Company();
		company.setType(type);
		return companyServiceImpl.findByType(company);
	}
	
	
	public static void main(String[] args) {	
		SpringApplication.run(MiniHrServiceApplication.class, args);
	}
}
