package com.miniHr.service.impl;

import com.miniHr.dao.CompanyDao;
import com.miniHr.dao.JobDao;
import com.miniHr.entity.Company;
import com.miniHr.entity.Job;
import com.miniHr.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职位服务实现类
 *
 * Created by DELL on 2017/6/15.
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    @Qualifier("companyDao")
    private CompanyDao companyDaoImpl;

    @Autowired
    private JobDao jobDaoImpl;

    /**
     * 新增一个职位
     * @param name
     * @param job
     * @param position
     * @param type
     */
    @Override
    public void create(String name, String job, String position, String type) {
        Company company = new Company();
        companyDaoImpl.addCompany(company);
    }

    /**
     * 根据id删除一个职位
     * @param id
     */
    @Override
    public void deleteById(Integer id) {

    }

    /**
     * 根据公司ID查询职位
     *
     * @param company
     * @return
     */
    @Override
    public List<Job> findByCompanyId(Company company) {
        return jobDaoImpl.selectJobByCompanyInfo(company);
    }
}
