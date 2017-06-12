package com.miniHr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = {"com.miniHr.controller","com.miniHr.service","com.miniHr.dao.impl"})
@Configuration
@ImportResource({"classpath:spring/pay-config.xml"})
public class MiniHrBootStrap {

	private static Logger log = LoggerFactory.getLogger(MiniHrBootStrap.class);


	public static void main(String[] args) {
		try{
			log.info("miniHr正在启动服务。。。");
			SpringApplication.run(MiniHrBootStrap.class, args);
		}catch(Exception e){
			e.printStackTrace();
			log.error("miniHr启动异常。。。");
		}
	}
	
}
