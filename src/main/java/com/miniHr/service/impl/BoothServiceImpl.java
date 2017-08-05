package com.miniHr.service.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniHr.dao.BoothDao;
import com.miniHr.entity.Booth;
import com.miniHr.entity.BoothExt;
import com.miniHr.service.BoothService;
import com.miniHr.vo.BoothVo;

@Service("boothService")
public class BoothServiceImpl implements BoothService {
    @Autowired
    private BoothDao boothDao;

    /** 查询所有展位信息 */
    @Override
    public List<Booth> getAllBooth() {
        return boothDao.queryAllBooth();
    }

    @Override
    public List<Booth> getBoothById(Integer id) {
        return boothDao.getBoothById(id);
    }

    public int updateBoothInfo(Booth booth) {
        return boothDao.updateBoothInfo(booth);
    }

    @Override
    public int updateBoothInfoByOriState(Booth booth, String oriState) throws Exception {
        BoothVo vo = new BoothVo();
        BeanUtils.copyProperties(vo, booth);
        vo.setOriState(oriState);
        return boothDao.updateBoothInfoWithState(vo);
    }

    @Override
    public List<Booth> queryBoothByStateAndCompanyId(Booth booth) {
        return boothDao.getBoothInfoByStateAndId(booth);
    }

    @Override
    public List<BoothExt> getAllBoothWithCompanyName() {
        return boothDao.getAllBoothWithCompanyName();
    }
}
