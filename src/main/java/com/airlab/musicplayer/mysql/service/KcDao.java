package com.airlab.musicplayer.mysql.service;

import org.springframework.stereotype.Component;

import com.airlab.musicplayer.model.musicinfo.Kc;
import com.airlab.musicplayer.mysql.api.IKcDao;
import com.airlab.musicplayer.mysql.core.MyBatisDao;

@Component
public class KcDao extends MyBatisDao<Kc> implements IKcDao {
	public static final String NAMESPACE = "mapper.KcMapper";

	@Override
	protected String getNameSpace() {
		return NAMESPACE;
	}

}
