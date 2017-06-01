package com.miniHr.entity;

/**
 * 用户等级
 * 1、普通用户，2、企业用户
 * @author Jacco
 */
public enum UserLevel {
	COMMON("1"),COMPANY("2");
	String level;

	UserLevel(String level){
		this.level = level;
	}
	
	public String getLevel(){
		return level;
	}
	
	public static String getUserLevel(UserLevel level){
		return level.getLevel();
	}
	
}
