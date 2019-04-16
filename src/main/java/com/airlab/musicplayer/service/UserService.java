package com.airlab.musicplayer.service;

import java.util.List;

import com.airlab.musicplayer.model.musicinfo.Users;

public interface UserService
{
	/**
	 * 通过用户名和密码注册
	 * @param username
	 * @param password
	 * @return
	 */
	public Users register(String username, String password);

	/**
	 * 通过id删除用户
	 * @param id
	 * @return
	 */
	public int deleteUser(int id);
	
	/**
	 * 返回所有的会员信息
	 * @return
	 */
	public List<Users> queryAllUsers();
}
