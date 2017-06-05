package com.miniHr.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniHr.comm.BoothState;
import com.miniHr.comm.RespCode;
import com.miniHr.comm.VariableKey;
import com.miniHr.entity.Booth;
import com.miniHr.pay.UnifiedorderPay;
import com.miniHr.service.BoothService;
import com.miniHr.util.StringUtil;

@RestController
public class BoothController {
	private static Logger log = LoggerFactory.getLogger(BoothController.class);
	
	@Autowired
	private BoothService boothService;
	
	@RequestMapping("/booth/query")
	public Map<String,Object> getAllBoothInfo(){
		log.info("查询展位信息");
		Map<String,Object> result = new HashMap<String,Object>();
		
		try{
			result.put(VariableKey.RETDATA, boothService.getAllBooth());
			result.put(VariableKey.RETCODE, RespCode.SUCCESS.getValue());
		}catch(Exception e){
			log.info("查询展位出现异常" + e);
			result.put(VariableKey.RETCODE, RespCode.FAIL.getValue());
		}
		return result;
	}
	
	/**
	 * 1、检查该公司是否已经提交过该展位的订单，但是没有支付
	 * 2、如果是首次提交，则更新展位状态为购中
	 */
	@RequestMapping("/booth/pay")
	public Map<String,Object> perchaseBooth(Integer boothId,String openId,String amount,Integer companyId) 
			throws Exception{
		/*响应结果*/
		Map<String,Object> result = new HashMap<String,Object>();
		
		if(StringUtil.isEmpty(openId) || StringUtil.isEmpty(boothId.toString())){
			result.put(VariableKey.RETCODE, RespCode.FAIL.getValue());
			result.put(VariableKey.RETDATA, "购买参数为空");
			return result;
		}
		
		Booth booth = boothService.getBoothById(boothId).get(0); //根据展位ID查询展位信息
		//此处判断  如果别的公司在购买该展位，则返回错误码 ，请用户重新购买展位
		if(BoothState.Saling.getState().equals(booth.getState()) && !companyId.equals(booth.getCompanyId())){
			result.put(VariableKey.RETCODE, RespCode.FAIL.getValue());
			result.put(VariableKey.RETDATA, "其他公司正在购买该展位");
			return result;
		}
		//构造参数对象
		booth.setCompanyId(companyId);
		booth.setState(BoothState.Saling.getState());
		boothService.updateBoothInfo(booth); // 将该展位设置为购买中。。。
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("amount", amount);
		map.put("boothid", boothId.toString());
		map.put("openid", openId);
		map.put("companyId", companyId.toString());
		log.info("构造支付报文" + map);
		
		result.put(VariableKey.RETDATA, UnifiedorderPay.WechatPay(map));
		result.put(VariableKey.RETCODE, RespCode.SUCCESS.getValue());
		return result;
	}
	
	@RequestMapping("/booth/payCompletly/{boothId}/{companyId}")
	public Map<String,Object> completePay(@PathVariable Integer boothId,@PathVariable Integer companyId,HttpServletRequest request,HttpServletResponse response)
			throws Exception{
		Map<String,String[]> reciveMap = request.getParameterMap();
		log.info("接收到的参数：" +reciveMap);
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		Booth booth = boothService.getBoothById(boothId).get(0);
		if(BoothState.Saled.getState().equals(booth.getState())){
			throw new Exception("该展位已被购买");
		}	
		
		booth.setState(BoothState.Saled.getState());
		booth.setCompanyId(companyId);
		int i = boothService.updateBoothInfo(booth);
		if(i == 1){
			result.put(VariableKey.RETCODE, RespCode.SUCCESS.getValue());
			result.put(VariableKey.RETDATA, "success");
		}
		return result;
	}
}
