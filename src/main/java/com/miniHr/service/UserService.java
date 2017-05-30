package com.miniHr.service;

import com.miniHr.entity.User;

public interface UserService {
	public User addUser(User user);
	
	public User getUserById(User user);
}
