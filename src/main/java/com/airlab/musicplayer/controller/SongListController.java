package com.airlab.musicplayer.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airlab.musicplayer.model.ResourceInfo;
import com.airlab.musicplayer.model.Response;
import com.airlab.musicplayer.model.musicinfo.SongList;
import com.airlab.musicplayer.service.ResourceInfoService;
import com.airlab.musicplayer.service.SongListService;

@Controller
@RequestMapping("songList")
public class SongListController {
	@Autowired
	private SongListService songListServiceImpl;
	
	@Autowired
	private ResourceInfoService resourceInfoServiceImpl;
	
	@RequestMapping(value = "/querySongListByNameAndDesc", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String querySongListByNameAndDesc(String name, String desc) {
		Response rs = new Response();
		List<SongList> result = songListServiceImpl.querySongListByNameAndDesc(name, desc);
		rs.addResult("result", result);
		return rs.toString();
	}
	
	@RequestMapping(value = "/querySongList", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String querySongList(String name, String desc) {
		Response rs = new Response();
		List<SongList> result = songListServiceImpl.querySongListByNameAndDesc(name, desc);
		for(int i = 0;i < result.size(); i ++){
			if(result.get(i).getCoverResourceId()!=null){
				ResourceInfo coverResourceInfo = resourceInfoServiceImpl.queryResourceInfo(result.get(i).getCoverResourceId());
				result.get(i).setCoverResourceInfo(coverResourceInfo);
			}	
	    }
		rs.addResult("result", result);
		return rs.toString();
	}
	
	@RequestMapping(value = "/insertSongList", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String insertSongList(String songListName, String songListDesc, String coverResourceId) {
		Response rs = new Response();
		SongList result = songListServiceImpl.insertSongList(songListName, songListDesc, coverResourceId);
		rs.addResult("result", result);
		return rs.toString();
	}
	
	@RequestMapping(value = "/updateSongList", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String updateSongList(Long id, String songListName, String songListDesc, String coverResourceId) {
		Response rs = new Response();
		songListServiceImpl.updateSongList(id, songListName, songListDesc, coverResourceId);
		return rs.toString();
	}
	
	@RequestMapping(value = "/deleteSongList", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")
	@ResponseBody public String deleteSong(Long id) {
		Response rs = new Response();
		songListServiceImpl.deleteSongList(id);
		return rs.toString();
	}
}
