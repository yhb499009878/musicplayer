package com.airlab.musicplayer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airlab.musicplayer.model.musicinfo.TAdmin;
import com.airlab.musicplayer.model.musicinfo.Users;
import com.airlab.musicplayer.mysql.api.ITAdminDao;
import com.airlab.musicplayer.mysql.api.IUsersDao;
import com.airlab.musicplayer.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private ITAdminDao tAdminDao;

	@Autowired
	private IUsersDao usersDao;

	@Override
	public TAdmin adminLogin(String username, String password) {
		TAdmin tAdmin = tAdminDao.queryByUsernameAndPassword(username, password);
		return tAdmin;
	}

	@Override
	public Users normalLogin(String username, String password) {
		Users users = usersDao.queryByUsernameAndPassword(username, password);
		return users;
	}

	@Override
	public Users register(String username, String password, String tel, String sex, String email, String isvip) {
		Users users = new Users();
		users.setUsername(username);
		users.setPassword(password);
		users.setTel(tel);
		users.setIsvip(isvip);
		users.setEmail(email);
		users.setSex(sex);
		usersDao.insert(users);
		return users;
	}

	@Override
	public List<Users> queryUsersByUsernameAndIsvip(String username, String isvip) {
		List<Users> users = usersDao.queryByUsernameAndIsvip(username, isvip);
		return users;
	}

	@Override
	public Users queryUsersByUsername(String username) {
		Users users = usersDao.queryByUsername(username);
		return users;
	}

}
