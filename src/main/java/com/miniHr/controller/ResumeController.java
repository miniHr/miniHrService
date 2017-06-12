package com.miniHr.controller;

import com.miniHr.comm.RespCode;
import com.miniHr.comm.VariableKey;
import com.miniHr.service.ResumeService;
import com.miniHr.util.JsonUtil;
import com.miniHr.vo.ResumeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 2017/6/12.
 */
@RestController
@RequestMapping(value = "/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeServiceImpl;

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
}
