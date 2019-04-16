package com.airlab.musicplayer.mysql.service;

import org.springframework.stereotype.Component;

import com.airlab.musicplayer.model.musicinfo.TLiuyan;
import com.airlab.musicplayer.mysql.api.ITLiuyanDao;
import com.airlab.musicplayer.mysql.core.MyBatisDao;

@Component
public class TLiuyanDao extends MyBatisDao<TLiuyan> implements ITLiuyanDao {
	public static final String NAMESPACE = "mapper.TLiuyanMapper";

	@Override
	protected String getNameSpace() {
		return NAMESPACE;
	}
}
