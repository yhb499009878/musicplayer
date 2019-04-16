package com.airlab.musicplayer.mysql.service;

import org.springframework.stereotype.Component;

import com.airlab.musicplayer.model.musicinfo.SongListMapping;
import com.airlab.musicplayer.mysql.api.ISonglistMappingDao;
import com.airlab.musicplayer.mysql.core.MyBatisDao;

@Component
public class SonglistMappingDao extends MyBatisDao<SongListMapping> implements ISonglistMappingDao {
	public static final String NAMESPACE = "mapper.SongListMappingMapper";

	@Override
	protected String getNameSpace() {
		return NAMESPACE;
	}

}
