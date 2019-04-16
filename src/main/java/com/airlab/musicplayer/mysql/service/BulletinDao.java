package com.airlab.musicplayer.mysql.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.airlab.musicplayer.model.musicinfo.Bulletin;
import com.airlab.musicplayer.mysql.api.IBulletinDao;
import com.airlab.musicplayer.mysql.core.MyBatisDao;

@Component
public class BulletinDao extends MyBatisDao<Bulletin> implements IBulletinDao {
	public static final String NAMESPACE = "mapper.BulletinMapper";

	@Override
	protected String getNameSpace() {
		return NAMESPACE;
	}

	@Override
	public List<Bulletin> queryBulletinByContent(String content) {
		return getSqlSession().selectList(NAMESPACE+".queryBulletinByContent", content);
	}
}
