package com.miniHr.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniHr.comm.RespCode;
import com.miniHr.comm.VariableKey;
import com.miniHr.entity.User;
import com.miniHr.service.UserService;

@RestController
public class UserController {
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/query")
	public Map<String,Object> queryById(String openId){
		Map<String,Object> result = new HashMap<String,Object>();
		
		User user = new User();
		user.setOpenId(openId);
		try{
			result.put(VariableKey.RETDATA, userService.getUserById(user));
			result.put(VariableKey.RETCODE, RespCode.SUCCESS.getValue());
		}catch (Exception e){
			log.info("查询用户异常：" + e);
			result.put(VariableKey.RETCODE, RespCode.FAIL.getValue());
		}
		return result;
	}
	
	@RequestMapping("/insert")
	public Map<String,Object> register(User user){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(VariableKey.RETCODE, RespCode.FAIL.getValue());
		
		user = userService.addUser(user);
		if(null != user.getId()){
			result.put(VariableKey.RETDATA, user);
			result.put(VariableKey.RETCODE, RespCode.SUCCESS.getValue());
		}	
		return result;
	}
}
