package com.miniHr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniHr.entity.User;
import com.miniHr.service.UserService;

@RestController
public class UserController {
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/query")
	public User queryById(String openId){
		User user = new User();
		user.setOpenId(openId);
		try{
			return userService.getUserById(user);
		}catch (Exception e){
			log.info("查询用户异常：" + e);
		}
		return null;
	}
	
	@RequestMapping("/insert")
	public User register(User user){
		user = userService.addUser(user);
		if(null != user.getId())
			return user;
		return null;
	}
}
