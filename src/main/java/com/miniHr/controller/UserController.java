package com.miniHr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniHr.entity.User;
import com.miniHr.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/query/{userId}")
	public User queryById(@PathVariable Integer userId){
		User user = new User();
		user.setId(userId);
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
