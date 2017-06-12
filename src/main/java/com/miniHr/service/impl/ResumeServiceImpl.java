package com.miniHr.service.impl;

import com.miniHr.comm.ResumeLevel;
import com.miniHr.dao.JobDao;
import com.miniHr.dao.ResumeDao;
import com.miniHr.dao.UserDao;
import com.miniHr.entity.Job;
import com.miniHr.entity.Resume;
import com.miniHr.service.ResumeService;
import com.miniHr.vo.ResumeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 简历授权信息实现类
 *
 * Created by DELL on 2017/6/12.
 */
@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private JobDao jobDaoImpl;

    @Autowired
    private ResumeDao resumeDaoImpl;

    /**
     * 1.新增简历授权
     *
     * @param resumeVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addResumeInfo(ResumeVo resumeVo) {
        Job job = new Job();
        job.setId(resumeVo.getJobId());
        job = jobDaoImpl.selectById(job);

        Resume resume = new Resume();
        resume.setJobId(resumeVo.getJobId());
        resume.setOpenid(resumeVo.getOpenId());
        resume.setCompanyId(job.getCompanyId());
        resume.setState(ResumeLevel.Processing.getLevel());
        return resumeDaoImpl.insert(resume);
    }
}
