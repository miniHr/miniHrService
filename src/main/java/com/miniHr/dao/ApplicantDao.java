package com.miniHr.dao;


public interface ApplicantDao {
	/**
	 * 根据type更新点击数
	 */
	void incrCountByType(String type);
	
	/**
	 * 获取指定type的人数 
	 */
	int getCountByType(String type);
}
