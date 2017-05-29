package com.miniHr.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanMap;
import org.springframework.stereotype.Service;

import com.miniHr.entity.PayRequest;
import com.miniHr.pay.UnifiedorderPay;
import com.miniHr.pay.WechatSecurity;
import com.miniHr.service.PayService;
import com.miniHr.util.HttpHelper;
import com.miniHr.util.StringUtil;

@Service(value="payService")
public class PayServiceImpl implements PayService {
	private String key;
	
	private String transUrl;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String,String> pay(PayRequest request) throws Exception {
		Map beanMap = new BeanMap(request);
		SortedMap<String,String> reqParams = new TreeMap<String,String>();
		reqParams.putAll(beanMap);
		reqParams.put("sign", WechatSecurity.sign(reqParams,key));
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("reqMsg", StringUtil.parseXML(reqParams));
		
		String prePayId = parseTransResult(HttpHelper.post(transUrl, params));
		
		if(StringUtil.isEmpty(prePayId))
			throw new Exception("支付过程出错");
		
		SortedMap<String,String> redirectParams = new TreeMap<String,String>();
		redirectParams.put("appId", request.getAppId());
		redirectParams.put("timeStamp", String.valueOf(System.currentTimeMillis()).substring(0,10));
		redirectParams.put("nonceStr", UnifiedorderPay.getRandStr());
		redirectParams.put("package", "prepay_id=" + prePayId);
		redirectParams.put("signType", "MD5");
		redirectParams.put("paySign",WechatSecurity.sign(redirectParams,key));
		return redirectParams;
	}

	/**解析微信支付报文*/
	private String parseTransResult(String xmlRes) {
		xmlRes = StringUtil.deleteCdata(xmlRes);
		String retCode=StringUtil.getTagValue(xmlRes, "return_code");//通讯状态
		
		if("SUCCESS".equals(retCode) && WechatSecurity.verify(xmlRes, key)){
			retCode=StringUtil.getTagValue(xmlRes,"result_code");//业务状态
			if("SUCCESS".equals(retCode)){
				retCode="00";
				return StringUtil.getTagValue(xmlRes, "prepay_id");
			}else{
				return "";
			}
		}
		return "";
	}

}
