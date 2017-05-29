package com.miniHr.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniHr.entity.Booth;
import com.miniHr.service.BoothService;

@RestController
public class BoothController {
	private static Logger log = LoggerFactory.getLogger(BoothController.class);
	
	@Autowired
	private BoothService boothSerivce;
	
	@RequestMapping("/booth/query")
	public List<Booth> getAllBoothInfo(){
		log.info("查询展位信息");
		return boothSerivce.getAllBooth();
	}
	
	public 
}
