package com.airlab.musicplayer.mysql.service;

import org.springframework.stereotype.Component;

import com.airlab.musicplayer.model.musicinfo.Ly;
import com.airlab.musicplayer.mysql.api.ILyDao;
import com.airlab.musicplayer.mysql.core.MyBatisDao;

@Component
public class LyDao extends MyBatisDao<Ly> implements ILyDao {
	public static final String NAMESPACE = "mapper.LyMapper";

	@Override
	protected String getNameSpace() {
		return NAMESPACE;
	}

}
