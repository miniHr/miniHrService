package com.miniHr.dao;

import com.miniHr.entity.Resume;

/**
 * 简历授权Dao
 * Created by DELL on 2017/6/12.
 */
public interface ResumeDao {
    /**
     * 通过ID查询简历授权信息
     *
     * @param resume
     * @return
     */
    Resume selectById(Resume resume);

    /**
     * 新增家里授权
     *
     * @param resume
     * @return
     */
    int insert(Resume resume);
}
