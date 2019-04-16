package com.airlab.musicplayer.service;

import java.util.List;

import com.airlab.musicplayer.model.musicinfo.Song;
import com.airlab.musicplayer.model.musicinfo.SongList;

public interface SongListService {
	
	public List<Song> querySongBySongListId(Long songListId);
	
	public List<SongList> querySongListByNameAndDesc(String name, String desc);

	public SongList insertSongList(String songListName, String songListDesc, String coverResourceId);

	public void deleteSongList(Long id);

	public void updateSongList(Long id, String songListName, String songListDesc, String coverResourceId);
	
	
}
