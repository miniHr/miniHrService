package com.miniHr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniHr.entity.User;
import com.miniHr.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/query/{openId}")
	public User queryById(@PathVariable String openId){
		User user = new User();
		user.setOpenId(openId);
		return userService.getUserById(user);
	}
	
	@RequestMapping("/insert")
	public User register(User user){
		user = userService.addUser(user);
		if(null != user.getId())
			return user;
		return null;
	}
}
