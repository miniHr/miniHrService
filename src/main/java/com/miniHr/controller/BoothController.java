package com.miniHr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniHr.entity.Booth;
import com.miniHr.entity.BoothState;
import com.miniHr.pay.UnifiedorderPay;
import com.miniHr.service.BoothService;
import com.miniHr.util.StringUtil;

@RestController
public class BoothController {
	private static Logger log = LoggerFactory.getLogger(BoothController.class);
	
	@Autowired
	private BoothService boothService;
	
	@RequestMapping("/booth/query")
	public List<Booth> getAllBoothInfo(){
		log.info("查询展位信息");
		return boothService.getAllBooth();
	}
	
	/**
	 * 1、更新当前选定展位的信息（此展位原始状态为 1未售出）
	 * 2、如果更新成功，则构造交易参数
	 * 
	 */
	@RequestMapping("/booth/pay")
	public Map<String,Object> perchaseBooth(Integer boothId,String openId,String amount,Integer companyId) 
			throws Exception{
		log.info("构造支付报文");
		if(StringUtil.isEmpty(openId) || StringUtil.isEmpty(openId))
			throw new Exception("购买展位的必填参数为空");
		
		Booth booth = new Booth();
		booth.setId(boothId);
		booth.setCompanyId(companyId);
		
		int i = boothService.updateBoothInfoByOriState(booth, BoothState.Unsaled.getState());
		
		if(i == 0)
			throw new Exception("当前展位状态不正确或者锁定展位的过程中发生异常,请重新选择展位");
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("amount", amount);
		map.put("boothid", boothId.toString());
		map.put("openid", openId);
		return UnifiedorderPay.WechatPay(map);
	}
	
	@RequestMapping("/booth/payCompletly/{boothId}/{companyId}")
	public Booth completePay(@PathVariable Integer boothId,@PathVariable Integer companyId) throws Exception{
		Booth booth = boothService.getBoothById(boothId);
		if(BoothState.Saled.getState().equals(booth.getState()))
			throw new Exception("该展位已被购买");
		
		booth.setState(BoothState.Saled.getState());
		booth.setCompanyId(companyId);
		int i = boothService.updateBoothInfo(booth);
		if(i == 1)
			return booth;
		return null;
	}
}
