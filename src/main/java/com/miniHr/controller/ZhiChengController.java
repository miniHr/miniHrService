package com.miniHr.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miniHr.service.BoothService;

@Controller
@RequestMapping(value = "/admin")
public class ZhiChengController {

    @Autowired
    private BoothService boothService;

    @RequestMapping(value = "/boothInfo")
    public String adminBooth(Map<String, Object> res) {
        res.put("boothInfos", boothService.getAllBoothWithCompanyName());
        return "index";
    }
}
