package com.miniHr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniHr.dao.BoothDao;
import com.miniHr.entity.Booth;
import com.miniHr.service.BoothService;

@Service("boothService")
public class BoothServiceImpl implements BoothService {
	@Autowired
	private BoothDao boothDao;
	
	/**查询所有展位信息*/
	@Override
	public List<Booth> getAllBooth() {
		return boothDao.queryAllBooth();
	}

	@Override
	public Booth getBoothById(Integer id) {
		return boothDao.getBoothById(id);
	}
	
	public int updateBoothInfo(Booth booth){
		return boothDao.updateBoothInfo(booth);
	}
}
