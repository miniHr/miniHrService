package com.miniHr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniHr.dao.UserDao;
import com.miniHr.entity.User;
import com.miniHr.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public User addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public User getUserById(User user) {
		return userDao.getUserById(user);
	}

}
