package com.miniHr.service;

import java.util.List;

import com.miniHr.entity.Booth;
import com.miniHr.entity.BoothExt;

public interface BoothService {
	/**
	 * 获得所有展位
	 */
	public List<Booth> getAllBooth();
	
	public List<BoothExt> getAllBoothWithCompanyName();
	
	/**
	 * 根据展位id获取展位信息
	 */
	public List<Booth> getBoothById(Integer id);
	
	public int updateBoothInfo(Booth booth);
	
	/**根据原展位状态更新*/
	public int updateBoothInfoByOriState(Booth booth,String oriState) throws Exception;
	
	public List<Booth> queryBoothByStateAndCompanyId(Booth booth);
}
