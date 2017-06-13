package com.miniHr.controller;

import com.miniHr.comm.RespCode;
import com.miniHr.comm.ResumeLevel;
import com.miniHr.comm.UserLevel;
import com.miniHr.comm.VariableKey;
import com.miniHr.entity.Resume;
import com.miniHr.entity.User;
import com.miniHr.service.ResumeService;
import com.miniHr.service.UserService;
import com.miniHr.util.JsonUtil;
import com.miniHr.vo.ResumeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2017/6/12.
 */
@RestController
@RequestMapping(value = "/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeServiceImpl;

    @Autowired
    private UserService userService;

    private static Logger log = LoggerFactory.getLogger(ResumeController.class);

    @GetMapping(value = "/insert")
    public String resume(String openId, int jobId) {
        log.info("openId = {}, jobId = {}", openId, jobId);

        ResumeVo resumeVo = new ResumeVo();
        resumeVo.setOpenId(openId);
        resumeVo.setJobId(jobId);
        int resumeId = resumeServiceImpl.addResumeInfo(resumeVo);

        Map<String,Object> retMap = new HashMap<>();
        retMap.put(VariableKey.RETCODE, RespCode.SUCCESS.getValue());

        Map<String, Object> retData = new HashMap<>();
        retData.put("keyID",resumeId);

        retMap.put(VariableKey.RETDATA,retData);
        return JsonUtil.toJson(retMap);
    }

    /**
     * 简历查询
     *
     * @param openId 用户openId
     * @param level 用户等级 1.个人用户2.企业用户
     * @param state 简历等级 1.处理中简历2.已收藏简历
     * @return
     */
    @GetMapping(value = "/query")
    public String queryResume(String openId, String level, String state) {
        Map<String,Object> retMap = new HashMap<>();
        Map<String, Object> retData = new HashMap<>();
        List<User> users = new ArrayList<>();

        User user = new User();
        user.setOpenId(openId);
        user = userService.getUserById(user);
        /**个人用户：显示个人简历*/
        if (UserLevel.INDIVIDUAL.getLevel().equals(level)) {
            users.add(user);
        /**企业用户：显示所在公司收到的简历*/
        } else if (UserLevel.PAYED_COMPANY_USER.getLevel().equals(level)) {
            if (!StringUtils.isEmpty(user.getCompanyId())) {
                Resume resume = new Resume();
                resume.setCompanyId(user.getCompanyId());
                resume.setState(state);
                resume.setOpenid(openId);
                users = resumeServiceImpl.queryUserInfosByCompanyUser(resume);
            } else {
                log.warn("this company user's = [{}] companyId is empty!", openId);
            }
        }
        retData.put(VariableKey.LISTINFOS, users);

        retMap.put(VariableKey.RETDATA, retData);
        retMap.put(VariableKey.RETCODE, RespCode.SUCCESS.getValue());

        return JsonUtil.toJson(retMap);
    }

    /**
     * 简历收藏
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/save")
    public String saveResume(int id) {
        Map<String,Object> retMap = new HashMap<>();
        retMap.put(VariableKey.RETCODE, updateResume(ResumeLevel.Store.getLevel(), id) == 1 ? RespCode.SUCCESS.getValue() : RespCode.FAIL.getValue());
        return JsonUtil.toJson(retMap);
    }

    /**
     * 简历删除
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/delete")
    public String deleteResume(int id) {
        Map<String,Object> retMap = new HashMap<>();
        retMap.put(VariableKey.RETCODE, updateResume(ResumeLevel.Delete.getLevel(), id) == 1 ? RespCode.SUCCESS.getValue() : RespCode.FAIL.getValue());
        return JsonUtil.toJson(retMap);
    }

    public int updateResume(String state, int id) {
        Resume resume = new Resume();
        resume.setId(id);
        resume.setState(state);
        return resumeServiceImpl.modifyByState(resume);
    }
}
