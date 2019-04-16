package com.airlab.musicplayer.mysql.service;

import org.springframework.stereotype.Component;

import com.airlab.musicplayer.model.musicinfo.Types;
import com.airlab.musicplayer.mysql.api.ITypesDao;
import com.airlab.musicplayer.mysql.core.MyBatisDao;

@Component
public class TypesDao extends MyBatisDao<Types> implements ITypesDao {
	public static final String NAMESPACE = "mapper.TypesMapper";

	@Override
	protected String getNameSpace() {
		return NAMESPACE;
	}

}
