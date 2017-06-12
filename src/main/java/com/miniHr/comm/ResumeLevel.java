package com.miniHr.comm;

/**
 * 简历等级
 *
 * 1.处理中简历 2.已收藏简历 3.被删除简历
 * @author Jacco
 */
public enum ResumeLevel {
	Processing("1"),Store("2"),Delete("3");
	String level;

	ResumeLevel(String level){
		this.level = level;
	}
	
	public String getLevel(){
		return level;
	}
	
	public static String getResumeLevel(ResumeLevel level){
		return level.getLevel();
	}
	
}
