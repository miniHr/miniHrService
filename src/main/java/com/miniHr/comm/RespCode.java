package com.miniHr.comm;

/**
 * 程序处理结果码
 * 00 成功       01 失败
 * @author Jacco
 */
public enum RespCode {
	SUCCESS("00"),FAIL("01");
	
	String value;
	
	RespCode(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
	public static String getRespValue(RespCode code){
		return code.getValue();
	}
}
