package com.airlab.musicplayer.mysql.api;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.airlab.musicplayer.model.musicinfo.Song;
import com.airlab.musicplayer.mysql.core.IDao;

public interface ISearchDao extends IDao<Song> {

	List<Song> findSongListByXName(@Param("name")String name,@Param("singerName")String singerName);
}
