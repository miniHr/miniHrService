package com.miniHr.service;

import com.miniHr.entity.User;

import java.util.List;

/**
 * 用户服务表
 */
public interface UserService {
	public User addUser(User user);
	
	public User getUserById(User user);
	
	public int updateUserInfo(User user);
	
	public int modifyUserLevelByOpenId(User user);

	/**
	 * 修改用户信息
	 *
	 * @param user
     */
	void modifyUserPhone(User user);

	/**
	 * 查找所有用户
	 * @return
	 */
	List<User> findAllUser();

	/**
	 * 按照日期时间选择
	 * @param begin
	 * @param end
	 * @return
	 */
	List<User> findUserByDate(String begin, String end);

	/**
	 * 根据时间删除简历
	 * @param begin
	 * @param end
	 * @return
	 */
	int deleteUserByDate(String begin,String end);
}
