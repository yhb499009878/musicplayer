package com.airlab.musicplayer.mysql.api;

import java.util.List;

import com.airlab.musicplayer.model.musicinfo.Users;
import com.airlab.musicplayer.mysql.core.IDao;

public interface IUsersDao extends IDao<Users>{

	Users queryByUsernameAndPassword(String username, String password);

	Users queryByUsername(String username);

	List<Users> queryByUsernameAndIsvip(String username, String isVip);

	List<Users> queryAllUsers();
}
