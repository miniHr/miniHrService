package com.miniHr.dao;

import java.util.List;

import com.miniHr.entity.Booth;
import com.miniHr.vo.BoothVo;

public interface BoothDao {
	
	/**获取所有展位*/
	public List<Booth> queryAllBooth();
	
	/**根据展位id更新展位信息*/
	public int updateBoothInfo(Booth booth);
	
	/**根据id查询展位信息*/
	public List<Booth> getBoothById(Integer id);
	
	/**根据原展位状态更新展位状态*/
	public int updateBoothInfoWithState(BoothVo vo);
	
	/**根据状态和展位id查询展位信息*/
	public List<Booth> getBoothInfoByStateAndId(Booth booth);
}
