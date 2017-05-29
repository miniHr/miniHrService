package com.miniHr.dao;

import java.util.List;

import com.miniHr.entity.Booth;

public interface BoothDao {
	
	/**获取所有展位*/
	public List<Booth> queryAllBooth();
	
	/**根据展位id更新展位信息*/
	public int updateBoothInfo(Booth booth);
	
	public Booth getBoothById(Integer id);
}
