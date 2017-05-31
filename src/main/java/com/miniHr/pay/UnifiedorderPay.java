package com.miniHr.pay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import org.mortbay.util.ajax.JSON;

import com.miniHr.util.DateUtil;
import com.miniHr.util.HttpHelper;
import com.miniHr.util.StringUtil;

public class UnifiedorderPay {
	public static final String appid="wxe79c9076ceb26a59";
	public static final String mchtId="1466649802";
	public static final String key="66666666666666668888888888888888";
	public static final String reqUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	public static final String base = "abcdefghijklmnopqrstuvwxyz0123456789"; 
	/**
	 * 用于生成32位随机字符串
	 * @return String
	 */
	public static String getRandStr(){
	    Random random = new Random();   
	    StringBuffer rand = new StringBuffer();   
	    for (int i = 0; i < 32; i++) {   
	        int number = random.nextInt(36);   
	        rand.append(base.charAt(number));   
	    }
	    return rand.toString();
	}
	//统一下单支付
	public static Map<String, Object> WechatPay(Map<String, String> map){
		Map<String, Object> resParams = new HashMap<String, Object>();
		String amount=map.get("amount");
		String body="展位购买-展位"+map.get("boothid");
		String openid=map.get("openid");
		SortedMap<String, String> parameters =getReqParameters(amount,body,openid);
        String xmlInfo = StringUtil.parseXML(parameters);
        System.out.println("reqMsg===="+xmlInfo);
        
        Map<String, Object> params = new HashMap<String, Object>();
		params.put("reqMsg", xmlInfo);
        String respXml=HttpHelper.post(reqUrl, params);
		
		System.out.println("respXml===="+respXml);
		resParams=getRespState(respXml);
		respXml = StringUtil.deleteCdata(respXml);
		if(resParams.get("resCode").equals("00")){
			resParams.put("redirectParamsMap", JSON.toString(getH5Map(respXml)));
		}
		return resParams;
		
	}
	//获取返回报文的交易状态
	public static Map<String, Object> getRespState(String respXml){
		Map<String, Object> resParams = new HashMap<String, Object>();
		respXml = StringUtil.deleteCdata(respXml);
		String retCode=StringUtil.getTagValue(respXml, "return_code");//通讯状态
		String retMsg = StringUtil.getTagValue(respXml, "return_msg");
		if("SUCCESS".equals(retCode) && WechatSecurity.verify(respXml, key)){
			retCode=StringUtil.getTagValue(respXml,"result_code");//业务状态
			if("SUCCESS".equals(retCode)){
				retCode="00";
			}else{
				retCode="01";
				retMsg=StringUtil.getTagValue(respXml, "err_code_des");
			}
		}else{
			retCode="01";
		}
		resParams.put("resCode", retCode);
		resParams.put("resMsg",retMsg);
		return resParams;
	}
	//组装请求参数
	private static SortedMap<String, String> getReqParameters(String amount,
			String body, String openid) {
		SortedMap<String, String> parameters = new TreeMap<String, String>();  
        parameters.put("appid", appid);  
        parameters.put("mch_id", mchtId);  
        parameters.put("device_info", "");  
        parameters.put("nonce_str", getRandStr());  
        parameters.put("sign_type", "MD5");  
        parameters.put("body", body); 
        parameters.put("detail", "");  
        parameters.put("attach", "");  
        parameters.put("out_trade_no",DateUtil.formatCurrDateTime(DateUtil.DF_YMDHMS)+openid.substring(0,10)); 
        parameters.put("fee_type", "CNY"); 
        parameters.put("total_fee", amount);  
        parameters.put("spbill_create_ip", "127.0.0.1");
        parameters.put("time_start", "");  
        parameters.put("time_expire", ""); 
        parameters.put("goods_tag", "");  
        parameters.put("notify_url", "http://116.228.64.57:80/its-web/strtx/1176/200309/utf-8");  
        parameters.put("trade_type", "JSAPI");  
        parameters.put("product_id", "");  
        parameters.put("limit_pay", "no_credit"); //no_credit 
        parameters.put("openid", openid);  
        parameters.put("sign", WechatSecurity.sign(parameters,key)); 
		return parameters;
	}
	//组装H5控件请求参数
	private static SortedMap<String, String> getH5Map(String respXml) {
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DF_YMDHMS);
		long millionSeconds = 0;
		try {
			millionSeconds = sdf.parse(DateUtil.formatCurrDateTime(DateUtil.DF_YMDHMS)).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//毫秒
		SortedMap<String, String> redirectParamsMap= new TreeMap<String, String>();
		redirectParamsMap.put("appId", appid);
		redirectParamsMap.put("timeStamp", String.valueOf(millionSeconds).substring(0,10));
		redirectParamsMap.put("nonceStr", getRandStr());
		redirectParamsMap.put("package", "prepay_id="+StringUtil.getTagValue(respXml,"prepay_id"));
		redirectParamsMap.put("signType", "MD5");
		redirectParamsMap.put("paySign",WechatSecurity.sign(redirectParamsMap,key));
		return redirectParamsMap;
	}
	
	public static void main(String[] args) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("amount", "1");
		map.put("boothid", "15");
		map.put("openid", "oCeMZ0WUojqOrmtZOA0f1-8qvJg0");
		System.out.println(WechatPay(map));
		
	}
}
