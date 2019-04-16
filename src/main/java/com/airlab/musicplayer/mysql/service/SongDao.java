package com.airlab.musicplayer.mysql.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.airlab.musicplayer.model.musicinfo.Song;
import com.airlab.musicplayer.mysql.api.ISongDao;
import com.airlab.musicplayer.mysql.core.MyBatisDao;

@Component
public class SongDao extends MyBatisDao<Song> implements ISongDao {

	public static final String NAMESPACE = "mapper.SongMapper";

	@Override
	protected String getNameSpace() {
		return NAMESPACE;
	}

	@Override
	public List<Song> querySongBySongNameAndSingerName(String songName, String singerName) {
		Map<String, Object> params = new HashMap<>();
		if (StringUtils.isNotEmpty(songName)) {
			params.put("songName", songName);
		}
		if (StringUtils.isNotEmpty(singerName)) {
			params.put("singerName", singerName);
		}
		return getSqlSession().selectList(NAMESPACE + ".querySongBySongNameAndSingerName", params);
	}
	
	@Override
	public List<Song> querySongByMusicListId(long songListId) {
		Map<String, Object> params = new HashMap<>();
		if (songListId!=0) {
			params.put("songListId", songListId);
		}
		return getSqlSession().selectList(NAMESPACE + ".querySongByMusicListId", params);
	}

}
