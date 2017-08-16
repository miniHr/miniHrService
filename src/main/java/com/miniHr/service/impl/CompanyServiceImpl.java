package com.miniHr.service.impl;

import java.util.List;

import com.miniHr.dao.UserDao;
import com.miniHr.dao.impl.BoothDaoImpl;
import com.miniHr.entity.Booth;
import com.miniHr.entity.CompanyExt;
import com.miniHr.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.miniHr.comm.UserLevel;
import com.miniHr.dao.CompanyDao;
import com.miniHr.entity.Company;
import com.miniHr.service.CompanyService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 企业信息服务实现类
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    @Qualifier("companyDao")
    private CompanyDao companyDaoImpl;

    @Autowired
    @Qualifier("userDao")
    private UserDao userdaoImpl;

    @Autowired
    @Qualifier("boothDao")
    private BoothDaoImpl boothDaoImpl;

    /**
     * 添加一个企业信息
     * @param companyExt
     * @return 新增记录的主键ID
     */
    @Override
    @Transactional
    public int insert(CompanyExt companyExt) {
        String authCode=String.valueOf(System.currentTimeMillis()).substring(7);
        companyExt.setAuthCode(authCode);
        int companyId = companyDaoImpl.addCompany(companyExt);

//        User user = new User();
//        user.setOpenId(companyExt.getOpenId());
//        user.setLevel(UserLevel.UNPAYED_COMPANY_USER.getLevel());
//        user.setCompanyId(companyId);
//        userdaoImpl.addUser(user);
        Booth booth=new Booth();
        booth.setId(companyExt.getBoothId());
        booth.setCompanyId(companyId);
        booth.setState("3");
        boothDaoImpl.updateBoothInfo(booth);
        return companyId;
    }

    /**
     * 查询企业信息
     * @param id
     * @return
     */
    @Override
    public Company selectCompanyInfo(int id) {
        return companyDaoImpl.selectCompanyInfoById(id);
    }

	@Override
	public int modifyBoothIdOfCompanyById(Company company) {
		return companyDaoImpl.updateBoothInfoOfCompanyById(company);
	}

	@Override
	public List<Company> findCompanyLimited(String limit) {
		return companyDaoImpl.findCompanyLimited(limit);
	}
}
