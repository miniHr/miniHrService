package com.miniHr.service;

import java.util.List;

import com.miniHr.entity.Booth;
import com.miniHr.entity.BoothExt;

public interface BoothService {
    /**
     * 获得所有展位
     */
    List<Booth> getAllBooth();

    /**
     * 获得所有展位及公司名称
     *
     * @return
     */
    List<BoothExt> getAllBoothWithCompanyName();

    /**
     * 分页查询展位及公司名称
     *
     * @return
     */
    List<BoothExt> getAllBoothWithCompanyNameByPage(Integer pageNum);

    /**
     * 查询展位总数
     *
     * @return
     */
    int getAllBoothInfoCount();

    /**
     * 根据展位id获取展位信息
     */
    List<Booth> getBoothById(Integer id);

    int updateBoothInfo(Booth booth);

    /**
     * 根据原展位状态更新
     */
    int updateBoothInfoByOriState(Booth booth, String oriState) throws Exception;

    List<Booth> queryBoothByStateAndCompanyId(Booth booth);
}
