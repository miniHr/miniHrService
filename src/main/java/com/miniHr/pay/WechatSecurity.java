package com.miniHr.pay;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.miniHr.util.StringUtil;

public class WechatSecurity {

	public static String sign(SortedMap<String, String> parameters, String key) {
		 String signSrc=sortFields(parameters)+"&key="+ key;
		 String signValue=MD5Encode(signSrc).toUpperCase();
		 return signValue;
	}
	
	public static boolean verify(String respXml, String key) {
		Document doc;
		try {
			doc = DocumentHelper.parseText(respXml); 
			SortedMap <String, String> map = new TreeMap<String, String>();
			List<Element> elements =  doc.getRootElement().elements();  
			for (Element element : elements) {  
				map.put(element.getName(), element.getTextTrim());  
			}
			map.remove("sign");
			String signValue=sign(map,key);
			String mac=StringUtil.getTagValue(respXml, "sign");
			if(signValue.equals(mac)) return true;
		} catch (DocumentException e) {
			throw new RuntimeException("获取返回签名源失败：" + e.getCause());
		}
		return false;  
	}
	
	
	
	/**
	 * 对数据做MD5摘要
	 * 
	 * @param aData
	 *            源数据
	 * @return 摘要结果
	 * @throws SecurityException
	 * @author nilomiao
	 * @since 2009-11-27
	 */
	public static String MD5Encode(String aData) throws SecurityException {
		String resultString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = bytes2HexString(md.digest(aData.getBytes("UTF-8")));
		} catch (Exception e) {
			throw new SecurityException("MD5运算失败");
		}
		return resultString;
	}
	

	/**
	 * 用于将所有参与传参的参数按照accsii排序（升序）后生成签名串
	 * @param packageFields
	 * @param avoidString除此字段以外的所以域
	 * @return 用于前面的串
	 */
	public static String sortFields( SortedMap<String, String> packageFields){
		StringBuilder sb = new StringBuilder();
		Set es = packageFields.entrySet();//所有参与传参的参数按照accsii排序（升序）  
		 Iterator it = es.iterator();  
	        while(it.hasNext()) {  
	            Map.Entry entry = (Map.Entry)it.next();  
	            String tmp = (String)entry.getKey();  
	            Object tmpValue = entry.getValue();  
	            if(null != tmpValue && !"".equals(tmpValue)) {  
	                sb.append("&"+tmp + "=" + tmpValue );  
	            }  
	        }
		return sb.toString().substring(1);
	}
	/**
	 * Byte数组转十六进制字符串字节间不用空格分隔
	 * 
	 * @param b
	 * @return
	 */
	public static String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}
	
}
