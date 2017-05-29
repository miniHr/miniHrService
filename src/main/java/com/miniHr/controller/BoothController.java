package com.miniHr.controller;

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
import com.miniHr.entity.PayRequest;
import com.miniHr.pay.UnifiedorderPay;
import com.miniHr.service.BoothService;
import com.miniHr.service.PayService;
import com.miniHr.util.DateUtil;
import com.miniHr.util.StringUtil;

@RestController
public class BoothController {
	private static Logger log = LoggerFactory.getLogger(BoothController.class);
	
	@Autowired
	private BoothService boothSerivce;
	@Autowired
	private PayService pay;
	
	@Autowired
	private PayRequest request;
	
	@RequestMapping("/booth/query")
	public List<Booth> getAllBoothInfo(){
		log.info("查询展位信息");
		return boothSerivce.getAllBooth();
	}
	
	@RequestMapping("/booth/pay")
	public Map<String,String> perChaseBooth(String boothId,String openId,String body) 
			throws Exception{
		log.info("构造支付报文");
		if(StringUtil.isEmpty(openId) || StringUtil.isEmpty(openId))
			throw new Exception("购买展位的必填参数为空");
		
		Booth booth = boothSerivce.getBoothById(Integer.parseInt(boothId));
		
		if(!BoothState.Unsaled.getState().equals(booth.getState()))
			throw new Exception("该展位已被购买或者购买中，请选择其他展位");
		booth.setState(BoothState.Saling.getState());
		boothSerivce.updateBoothInfo(booth);
		
		request.setBody("支付展位" + boothId);
		request.setNonceStr(UnifiedorderPay.getRandStr());
		request.setTotalFee(booth.getPrice() + "");
		request.setOutTraceNo(DateUtil.formatCurrDateTime(DateUtil.DF_YMDHMS) + openId.substring(0,10));
		return pay.pay(request);
	}
	
	@RequestMapping("/booth/payCompletly/{companyId}")
	public Booth completePay(Integer boothId,@PathVariable Integer companyId) throws Exception{
		Booth booth = boothSerivce.getBoothById(boothId);
		if(BoothState.Saled.getState().equals(booth.getState()))
			throw new Exception("该展位已被购买");
		
		booth.setState(BoothState.Saled.getState());
		booth.setCompanyId(companyId);
		int i = boothSerivce.updateBoothInfo(booth);
		if(i == 1)
			return booth;
		return null;
	}
}
