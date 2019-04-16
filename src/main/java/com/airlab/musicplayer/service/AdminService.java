package com.airlab.musicplayer.service;

import java.util.List;

import com.airlab.musicplayer.model.musicinfo.TAdmin;
import com.airlab.musicplayer.model.musicinfo.Users;

public interface AdminService {
	/**
	 * 管理员登录
	 * @param username
	 * @param password
	 * @return
	 */
	public TAdmin adminLogin(String username, String password);
	
	/**
	 * 普通人员登录
	 * @param username
	 * @param password
	 * @return
	 */
	public Users normalLogin(String username, String password);
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param tel
	 * @param sex
	 * @param email
	 * @param isvip
	 * @return
	 */
	public Users register(String username, String password, String tel, String sex, String email, String isvip);

	/**
	 * 
	 * @param username
	 * @param isvip
	 * @return
	 */
	public List<Users> queryUsersByUsernameAndIsvip(String username, String isvip);

	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	public Users queryUsersByUsername(String username);
	
}
