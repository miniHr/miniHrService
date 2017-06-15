package com.miniHr.dao;

import com.miniHr.entity.Company;
import com.miniHr.entity.Job;

import java.util.List;

/**
 * 职位信息Dao
 *
 * Created by DELL on 2017/6/12.
 */
public interface JobDao {
    /**
     * 通过ID查询job信息
     *
     * @param job
     * @return
     */
    Job selectById(Job job);

    /**
     * 通过公司信息查询职位信息
     *
     * @param company
     * @return
     */
    List<Job> selectJobByCompanyInfo(Company company);
}
