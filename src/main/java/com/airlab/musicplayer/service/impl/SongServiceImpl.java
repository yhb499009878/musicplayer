package com.airlab.musicplayer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airlab.musicplayer.model.musicinfo.Song;
import com.airlab.musicplayer.model.musicinfo.SongListMapping;
import com.airlab.musicplayer.mysql.api.ISongDao;
import com.airlab.musicplayer.mysql.api.ISonglistMappingDao;
import com.airlab.musicplayer.service.SongService;

@Service
public class SongServiceImpl implements SongService {

	@Autowired
	private ISongDao songDao;
	
	@Autowired
	private ISonglistMappingDao songlistMappingDao;

	@Override
	public Song querySongById(long id) {
		return songDao.selectById(id);
	}
	
	@Override
	public List<Song> querySongBySongNameAndSingerName(String songName, String singerName) {
		return songDao.querySongBySongNameAndSingerName(songName, singerName);
	}
	
	@Override
	public List<Song> querySongByMusicListId(long songListId) {
		return songDao.querySongByMusicListId(songListId);
	}

	@Override
	public Song insertSong(String songName, String singerName, String songType, String posterResourceId,
			String songResourceId, String lyricsResourceId) {
		Song song = new Song();
		song.setSongName(songName);
		song.setSingerName(singerName);
		song.setSongType(songType);
		song.setPosterResourceId(posterResourceId);
		song.setSongResourceId(songResourceId);
		song.setLyricsResourceId(lyricsResourceId);

		song = songDao.insert(song);
		return song;
	}

	@Override
	public void deleteSong(Long id) {
		songDao.deleteById(id);
	}

	@Override
	public void updateSong(Long id, String songName, String singerName, String songType, String posterResourceId,
			String songResourceId, String lyricsResourceId) {
		Song song = new Song();
		song.setId(id);
		song.setSongName(songName);
		song.setSingerName(singerName);
		song.setSongType(songType);
		song.setPosterResourceId(posterResourceId);
		song.setSongResourceId(songResourceId);
		song.setLyricsResourceId(lyricsResourceId);
		songDao.updateById(song);
	}

	@Override
	public void insertSongToSongList(Long songId, Long songListId) {
		SongListMapping songListMapping = new SongListMapping(songId, songListId);
		songlistMappingDao.insert(songListMapping);
	}

}
