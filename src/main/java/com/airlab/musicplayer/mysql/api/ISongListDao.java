package com.airlab.musicplayer.mysql.api;

import java.util.List;

import com.airlab.musicplayer.model.musicinfo.Song;
import com.airlab.musicplayer.model.musicinfo.SongList;
import com.airlab.musicplayer.mysql.core.IDao;

public interface ISongListDao extends IDao<SongList> {
	public List<SongList> querySongListByNameAndDesc(String songListName, String songListDesc);
	
	public List<Song> querySongBySongListId(Long songListId);
}
