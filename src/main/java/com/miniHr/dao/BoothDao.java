package com.miniHr.dao;

import java.util.List;

import com.miniHr.entity.Booth;
import com.miniHr.entity.BoothExt;
import com.miniHr.vo.BoothVo;

public interface BoothDao {

    /**
     * 获取所有展位
     *
     * @return
     */
    List<Booth> queryAllBooth();

    /**
     * 获得所有展位及公司名称
     *
     * @return
     */
    List<BoothExt> getAllBoothWithCompanyName();

    /**
     * 分页查询展位及公司名称
     *
     * @param pageNum
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
     * 根据展位id更新展位信息
     *
     * @param booth
     * @return
     */
    int updateBoothInfo(Booth booth);

    /**
     * 根据id查询展位信息
     *
     * @param id
     * @return
     */
    List<Booth> getBoothById(Integer id);

    /**
     * 根据原展位状态更新展位状态
     *
     * @param vo
     * @return
     */
    int updateBoothInfoWithState(BoothVo vo);

    /**
     * 根据状态和展位id查询展位信息
     *
     * @param booth
     * @return
     */
    List<Booth> getBoothInfoByStateAndId(Booth booth);
}
