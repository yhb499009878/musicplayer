package com.airlab.musicplayer.mysql.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.airlab.musicplayer.model.musicinfo.TAdmin;
import com.airlab.musicplayer.mysql.api.ITAdminDao;
import com.airlab.musicplayer.mysql.core.MyBatisDao;

@Component
public class TAdminDao extends MyBatisDao<TAdmin> implements ITAdminDao {
	public static final String NAMESPACE = "mapper.TAdminMapper";

	@Override
	protected String getNameSpace() {
		return NAMESPACE;
	}

	@Override
	public TAdmin queryByUsernameAndPassword(String username, String password) {
		Map<String,Object> params = new HashMap<>();
		if(StringUtils.isNotEmpty(username)){
			params.put("username", username);
		}
		if(StringUtils.isNotEmpty(password)){
			params.put("password", password);
		}
		return getSqlSession().selectOne(NAMESPACE + ".queryByUsernameAndPassword", params);
	}
}
