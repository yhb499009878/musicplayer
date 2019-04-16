package com.airlab.musicplayer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airlab.musicplayer.model.musicinfo.Song;
import com.airlab.musicplayer.model.musicinfo.SongList;
import com.airlab.musicplayer.mysql.service.SongListDao;
import com.airlab.musicplayer.service.SongListService;

@Service
public class SongListServiceImpl implements SongListService {

	@Autowired
	private SongListDao songListDao;

	@Override
	public List<SongList> querySongListByNameAndDesc(String name, String desc) {
		return songListDao.querySongListByNameAndDesc(name, desc);
	}

	@Override
	public SongList insertSongList(String songListName, String songListDesc, String coverResourceId) {
		SongList songList = new SongList();
		songList.setSongListName(songListName);
		songList.setSongListDesc(songListDesc);
		songList.setCoverResourceId(coverResourceId);
		songList = songListDao.insert(songList);
		return songList;
	}

	@Override
	public void deleteSongList(Long id) {
		songListDao.deleteById(id);
	}

	@Override
	public void updateSongList(Long id, String songListName, String songListDesc, String coverResourceId) {
		SongList songList = new SongList();
		songList.setId(id);
		songList.setSongListName(songListName);
		songList.setSongListDesc(songListDesc);
		songList.setCoverResourceId(coverResourceId);
		songListDao.updateById(songList);
	}

	@Override
	public List<Song> querySongBySongListId(Long songListId) {
		return songListDao.querySongBySongListId(songListId);
	}

}
