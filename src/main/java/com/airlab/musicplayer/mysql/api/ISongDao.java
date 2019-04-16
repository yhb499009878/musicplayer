package com.airlab.musicplayer.mysql.api;

import java.util.List;

import com.airlab.musicplayer.model.musicinfo.Song;
import com.airlab.musicplayer.mysql.core.IDao;

public interface ISongDao extends IDao<Song>{
	public List<Song> querySongBySongNameAndSingerName(String songName, String singerName);

	List<Song> querySongByMusicListId(long songListId);
}
