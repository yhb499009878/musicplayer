package com.airlab.musicplayer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airlab.musicplayer.model.ResourceInfo;
import com.airlab.musicplayer.model.Response;
import com.airlab.musicplayer.model.musicinfo.Song;
import com.airlab.musicplayer.service.ResourceInfoService;
import com.airlab.musicplayer.service.SongService;

@Controller
@RequestMapping("song")
public class SongController {
	
	@Autowired
	private SongService songServiceImpl;
	
	@Autowired
	private ResourceInfoService resourceInfoServiceImpl;
	
	@RequestMapping(value = "/querySongBySongNameAndSingerName", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String querySongBySongNameAndSingerName(String songName, String singerName) {
		Response rs = new Response();
		List<Song> result = songServiceImpl.querySongBySongNameAndSingerName(songName, singerName);
		rs.addResult("result", result);
		return rs.toString();
	}
	
	@RequestMapping(value = "/querySongById", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String querySongById(long musicId) {
		Response rs = new Response();
		Song result = songServiceImpl.querySongById(musicId);
		if(!result.getPosterResourceId().equals("")){
			ResourceInfo postResourceInfo = resourceInfoServiceImpl.queryResourceInfo(result.getPosterResourceId());
			result.setPosterResourceInfo(postResourceInfo);
		}
		if(!result.getSongResourceId().equals("")){
			ResourceInfo songResourceInfo = resourceInfoServiceImpl.queryResourceInfo(result.getSongResourceId());
			result.setSongResourceInfo(songResourceInfo);
		}
		if(!result.getLyricsResourceId().equals("")){
			ResourceInfo lyricsResourceInfo = resourceInfoServiceImpl.queryResourceInfo(result.getLyricsResourceId());
			result.setLyricsResourceInfo(lyricsResourceInfo);
		}
		rs.addResult("result", result);
		return rs.toString();
	}
	
	@RequestMapping(value = "/querySongByMusicListId", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String querySongByMusicListId(long musicListId) {
		Response rs = new Response();
		List<Song> result = songServiceImpl.querySongByMusicListId(musicListId);
		rs.addResult("result", result);
		return rs.toString();
	}
	
	@RequestMapping(value = "/insertSong", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String insertSong(String songName, String singerName, String songType,
			String posterResourceId, String songResourceId, String lyricsResourceId) {
		Response rs = new Response();
		Song result = songServiceImpl.insertSong(songName, singerName, songType, posterResourceId, songResourceId, lyricsResourceId);
		rs.addResult("result", result);
		return rs.toString();
	}
	
	@RequestMapping(value = "/addToSongList", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String addToSongList(Long songId, Long songListId) {
		Response rs = new Response();
		songServiceImpl.insertSongToSongList(songId, songListId);
		return rs.toString();
	}
	
	@RequestMapping(value = "/updateSong", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String updateSong(Long id, String songName, String singerName, String songType,
			String posterResourceId, String songResourceId, String lyricsResourceId) {
		Response rs = new Response();
		songServiceImpl.updateSong(id, songName, singerName, songType, posterResourceId, songResourceId, lyricsResourceId);
		return rs.toString();
	}
	
	@RequestMapping(value = "/deleteSong", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String deleteSong(Long id) {
		Response rs = new Response();
		songServiceImpl.deleteSong(id);
		return rs.toString();
	}
}
