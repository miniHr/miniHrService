package com.miniHr.dao;

import com.miniHr.entity.User;

public interface UserDao {
	/**根据用户id获取用户信息*/
	public User getUserById(User user);
	
	/**增加用户*/
	public User addUser(User user);
	
	/**更新用户级别*/
	public int updateUserLevelInfoById(User user);
}
