package com.airlab.musicplayer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airlab.musicplayer.model.musicinfo.Users;
import com.airlab.musicplayer.mysql.api.IUsersDao;
import com.airlab.musicplayer.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private IUsersDao usersDao;
	
	@Override
	public Users register(String username, String password)
	{
		Users users = new Users();
		users.setUsername(username);
		users.setPassword(password);
		usersDao.insert(users);
		return users;
	}

	@Override
	public int deleteUser(int id)
	{
		return usersDao.deleteById(id);
	}

	@Override
	public List<Users> queryAllUsers()
	{
		List<Users> queryAllUsers = usersDao.queryAllUsers();
		return queryAllUsers;
	}

}
