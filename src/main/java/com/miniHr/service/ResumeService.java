package com.miniHr.service;

import com.miniHr.entity.Resume;
import com.miniHr.entity.User;
import com.miniHr.vo.ResumeVo;

import java.util.List;

/**
 * 简历授权相关服务接口
 *
 * Created by DELL on 2017/6/12.
 */
public interface ResumeService {
    /**
     * 1.新增简历授权
     *
     * @param resumeVo
     * @return
     */
    int addResumeInfo(ResumeVo resumeVo);

    /**
     * 2.企业用户获取所在公司的求职简历
     *
     * @param resume
     * @return
     */
    List<User> queryUserInfosByCompanyUser(Resume resume);
}
