package com.airlab.musicplayer.mysql.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.airlab.musicplayer.model.musicinfo.Song;
import com.airlab.musicplayer.mysql.api.ISearchDao;
import com.airlab.musicplayer.mysql.core.MyBatisDao;

@Component
public class SearchDao extends MyBatisDao<Song> implements ISearchDao{

	public static final String NAMESPACE = "mapper.SongMapper";
	private Map<String,String> params = null;

	@Override
	protected String getNameSpace() {
		return NAMESPACE;
	}

	@Override
	public List<Song> findSongListByXName(String name, String singerName) {
		params = new HashMap<>();
		if(StringUtils.isNotEmpty(name)){
			params.put("name", name);
		}
		if(StringUtils.isNotEmpty(singerName)){
			params.put("singerName", singerName);
		}
		return getSqlSession().selectList(NAMESPACE + ".findSongListByXName", params);
	}
}
