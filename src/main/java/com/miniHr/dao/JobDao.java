package com.miniHr.dao;

import com.miniHr.entity.Job;

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
}
