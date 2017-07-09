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

	@Override
	public int modifyUserLevelByOpenId(User user) {
		return userDao.updateUserLevelInfoById(user);
	}

	/**
	 * 修改用户信息
	 *
	 * @param user
	 */
	@Override
	public void modifyUserPhone(User user) {
		userDao.updateUserPhone(user);
	}

	@Override
	public int updateUserInfo(User user) {
		return userDao.updateUserInfo(user);
	}
}
