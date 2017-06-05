package com.miniHr.comm;

/**
 * 用户等级
 * 1、个人，2、已付费企业用户, 3、未付费企业用户
 * @author Jacco
 */
public enum UserLevel {
	INDIVIDUAL("1"),
	PAYED_COMPANY_USER("2"),
	UNPAYED_COMPANY_USER("3");
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
