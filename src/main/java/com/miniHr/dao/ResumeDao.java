package com.miniHr.dao;

import com.miniHr.entity.Resume;
import com.miniHr.entity.User;

import java.util.List;

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

    /**
     * 企业用户获取所在公司收到的所有简历信息
     * @param resume
     * @return
     */
    List<User> selectByCompanyUser(Resume resume);

    /**
     * 通过对象更新对象
     *
     * @param resume
     * @return
     */
    int updateResumeInfo(Resume resume);
}
