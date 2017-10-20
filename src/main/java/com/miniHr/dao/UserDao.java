package com.miniHr.dao;

import com.miniHr.entity.User;

import java.util.List;

public interface UserDao {
	/**根据用户id获取用户信息*/
	public User getUserById(User user);
	
	/**增加用户*/
	public User addUser(User user);
	
	/**修改用户信息*/
	public int updateUserInfo(User user);
	
	/**更新用户级别*/
	public int updateUserLevelInfoById(User user);

	/**
	 * 更新用户信息
	 *
	 * @param user
     */
	void updateUserPhone(User user);

    /**
     * 查找所有简历
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
