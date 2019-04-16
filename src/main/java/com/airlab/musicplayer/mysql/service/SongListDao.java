package com.airlab.musicplayer.mysql.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.airlab.musicplayer.model.musicinfo.Song;
import com.airlab.musicplayer.model.musicinfo.SongList;
import com.airlab.musicplayer.mysql.api.ISongListDao;
import com.airlab.musicplayer.mysql.core.MyBatisDao;

@Component
public class SongListDao extends MyBatisDao<SongList> implements ISongListDao {
	public static final String NAMESPACE = "mapper.SongListMapper";
	
	@Override
	protected String getNameSpace() {
		return NAMESPACE;
	}

	@Override
	public List<SongList> querySongListByNameAndDesc(String songListName, String songListDesc) {
		Map<String, Object> params = new HashMap<>();
		if (StringUtils.isNotEmpty(songListName)) {
			params.put("songListName", songListName);
		}
		if (StringUtils.isNotEmpty(songListDesc)) {
			params.put("songListDesc", songListDesc);
		}
		return getSqlSession().selectList(NAMESPACE + ".querySongListByNameAndDesc", params);
	}

	@Override
	public List<Song> querySongBySongListId(Long songListId) {
		return getSqlSession().selectList(NAMESPACE + ".querySongBySongListId", songListId);
	}

}
