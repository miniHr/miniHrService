package com.miniHr.service;

import com.miniHr.entity.Company;
import com.miniHr.entity.Job;

import java.util.List;

/**
 * 职位服务接口
 *
 * Created by DELL on 2017/6/15.
 */
public interface JobService {
    /**
     * 新增一个职位
     *
     * @param name
     * @param job
     * @param position
     * @param type
     */
    void create(String name,String job,String position,String type);

    /**
     * 根据id删除一个职位
     * @param id
     */
    void deleteById	(Integer id);

    /**
     * 根据公司ID查询职位
     *
     * @param company
     * @return
     */
    List<Job> findByCompanyId(Company company);
}
