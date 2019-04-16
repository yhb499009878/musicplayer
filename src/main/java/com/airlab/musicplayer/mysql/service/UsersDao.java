package com.airlab.musicplayer.mysql.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.airlab.musicplayer.model.musicinfo.Users;
import com.airlab.musicplayer.mysql.api.IUsersDao;
import com.airlab.musicplayer.mysql.core.MyBatisDao;

@Component
public class UsersDao extends MyBatisDao<Users> implements IUsersDao{
	public static final String NAMESPACE = "mapper.UsersMapper";

	@Override
	protected String getNameSpace() {
		return NAMESPACE;
	}

	@Override
	public Users queryByUsernameAndPassword(String username, String password) {
		Map<String,Object> params = new HashMap<>();
		if(StringUtils.isNotEmpty(username)){
			params.put("username", username);
		}
		if(StringUtils.isNotEmpty(password)){
			params.put("password", password);
		}
		return getSqlSession().selectOne(NAMESPACE + ".queryByUsernameAndPassword", params);
	}

	@Override
	public Users queryByUsername(String username) {
		return getSqlSession().selectOne(NAMESPACE + ".queryByUsername", username);
	}

	@Override
	public List<Users> queryByUsernameAndIsvip(String username, String isvip) {
		Map<String,Object> params = new HashMap<>();
		if(StringUtils.isNotEmpty(username)){
			params.put("username", username);
		}
		if(StringUtils.isNotEmpty(isvip)){
			params.put("isvip", isvip);
		}
		return getSqlSession().selectList(NAMESPACE + ".queryByUsernameAndIsvip", params);
	}
	
	@Override
	public List<Users> queryAllUsers()
	{
		List<Users> selectList = getSqlSession().selectList(NAMESPACE + ".queryAllUsers");
		return selectList;
	}
}
