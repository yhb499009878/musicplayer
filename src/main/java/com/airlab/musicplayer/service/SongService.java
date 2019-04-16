package com.airlab.musicplayer.service;

import java.util.List;

import com.airlab.musicplayer.model.musicinfo.Song;

public interface SongService {
	public List<Song> querySongBySongNameAndSingerName(String songName, String singerName);
	
	public List<Song> querySongByMusicListId(long songListId);

	public Song insertSong(String songName, String singerName, String songType, String posterResourceId,
			String songResourceId, String lyricsResourceId);
	
	public Song querySongById(long id);
	
	public void deleteSong(Long id);

	public void updateSong(Long id, String songName, String singerName, String songType, String posterResourceId,
			String songResourceId, String lyricsResourceId);
	
	public void insertSongToSongList(Long songId, Long songListId);

}
