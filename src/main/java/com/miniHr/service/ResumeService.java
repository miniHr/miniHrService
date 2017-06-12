package com.miniHr.service;

import com.miniHr.vo.ResumeVo;

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
}
