package com.miniHr.service.impl;

import com.miniHr.comm.ResumeLevel;
import com.miniHr.dao.CompanyDao;
import com.miniHr.dao.JobDao;
import com.miniHr.dao.ResumeDao;
import com.miniHr.entity.Company;
import com.miniHr.entity.Job;
import com.miniHr.entity.Resume;
import com.miniHr.entity.User;
import com.miniHr.service.ResumeService;
import com.miniHr.vo.ResumeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Autowired
    @Qualifier("companyDao")
    private CompanyDao companyDaoImpl;

    /**
     * 1.新增简历授权
     *
     * @param resumeVo
     * @return booth id
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
        if (resumeDaoImpl.insert(resume) <= 0) {
            throw new RuntimeException("resume fails to insert into table.");
        }

        Company company = companyDaoImpl.selectCompanyInfoById(job.getCompanyId());
        return company.getBoothId();
    }

    /**
     * 2.企业用户获取所在公司的求职简历
     *
     * @param resume
     * @return
     */
    @Override
    public List<User> queryUserInfosByCompanyUser(Resume resume) {
        return resumeDaoImpl.selectByCompanyUser(resume);
    }

    /**
     * 3.更改简历授权状态
     *
     * @param resume
     * @return 更新影响的记录条数
     */
    @Override
    public int modifyByState(Resume resume) {
        return resumeDaoImpl.updateResumeInfo(resume);
    }
}
