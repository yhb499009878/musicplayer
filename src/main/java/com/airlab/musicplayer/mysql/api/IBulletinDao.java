package com.airlab.musicplayer.mysql.api;

import java.util.List;

import com.airlab.musicplayer.model.musicinfo.Bulletin;
import com.airlab.musicplayer.mysql.core.IDao;

public interface IBulletinDao extends IDao<Bulletin> {
	public List<Bulletin> queryBulletinByContent(String content);
}
